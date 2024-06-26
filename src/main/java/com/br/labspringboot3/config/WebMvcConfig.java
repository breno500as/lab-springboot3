package com.br.labspringboot3.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Value("${cors.origin.patterns:default}")
	private String corsOriginPatterns;

	@Override
	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/**").allowedMethods("*").allowedOrigins(this.corsOriginPatterns.split(","))
				.allowCredentials(true);

	}

}
