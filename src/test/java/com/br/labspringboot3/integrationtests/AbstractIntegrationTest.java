package com.br.labspringboot3.integrationtests;

import java.util.Map;
import java.util.stream.Stream;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;

@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public class AbstractIntegrationTest {

	static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

		static PostgreSQLContainer<?> postgresqlContainer = new PostgreSQLContainer("postgres:16.0");

		private static void startContainers() {
			Startables.deepStart(Stream.of(postgresqlContainer)).join();
		}

		public static Map<String, String> createConnectionConfiguration() {
			return Map.of("spring.datasource.url", postgresqlContainer.getJdbcUrl(), "spring.datasource.username",
					postgresqlContainer.getUsername(), "spring.datasource.password", postgresqlContainer.getPassword());
		}

		@Override
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public void initialize(ConfigurableApplicationContext applicationContext) {

			startContainers();

			final ConfigurableEnvironment e = applicationContext.getEnvironment();
			MapPropertySource testContainers = new MapPropertySource("testcontainers",
					(Map) createConnectionConfiguration());
			e.getPropertySources().addFirst(testContainers);

		}

	}

}
