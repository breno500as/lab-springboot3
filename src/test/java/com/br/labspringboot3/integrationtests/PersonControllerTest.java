package com.br.labspringboot3.integrationtests;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.br.labspringboot3.dto.AccountCredentialsDTO;
import com.br.labspringboot3.dto.PersonDTO;
import com.br.labspringboot3.dto.TokenDTO;
import com.br.labspringboot3.util.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class PersonControllerTest extends AbstractIntegrationTest {

	private static RequestSpecification specification;
	
	private static ObjectMapper objectMapper;

	private static PersonDTO person;

	@BeforeAll
	public static void setup() {
		objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		person = new PersonDTO();
	}

	@Test
	@Order(0)
	void auth() {
		final AccountCredentialsDTO user = new AccountCredentialsDTO("Breno", "admin123");
		
		var accessToken = given()
				 .contentType(Constants.CONTENT_TYPE_JSON)
				 .basePath("/auth/signin")
				 .port(Constants.SERVER_PORT)
				 .body(user)
				 .when()
				 .post()
				 .then()
				 .statusCode(200)
				 .extract()
				 .body()
				 .as(TokenDTO.class);

		Assertions.assertNotNull(accessToken);
		Assertions.assertNotNull(accessToken.getToken());
		
		specification = new RequestSpecBuilder()
				.addHeader(Constants.HEADER_AUTHORIZATION, "Bearer " + accessToken.getToken())
				.setBasePath("/api/persons")
				.setPort(Constants.SERVER_PORT)
					.addFilter(new RequestLoggingFilter(LogDetail.ALL))
					.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();

	}
	
	@Test
	@Order(1)
	void testCreate() throws JsonMappingException, JsonProcessingException {
		
		mockPerson();
		
		var content = given().spec(specification)
				.contentType(Constants.CONTENT_TYPE_JSON)
				.body(person)
				.when()
				.post()
				.then()
				.statusCode(201)
				.extract()
			    .body()
				.asString();
		
		PersonDTO persistedPerson = objectMapper.readValue(content, PersonDTO.class);
		person = persistedPerson;
		
		assertNotNull(persistedPerson);
		
		assertNotNull(persistedPerson.getId());
		assertNotNull(persistedPerson.getName());
		 
		
		assertTrue(persistedPerson.getId() > 0);
		
		assertEquals("Testando 123", persistedPerson.getName());
	 
	}
	
	
	@Test
	@Order(2)
	void testUpdate() throws JsonMappingException, JsonProcessingException {
		
		person.setName("Test update");
		
		var content = given().spec(specification)
				.contentType(Constants.CONTENT_TYPE_JSON)
				.body(person)
				.when()
				.put()
				.then()
				.statusCode(200)
				.extract()
			    .body()
				.asString();
		
		PersonDTO updatedPerson = objectMapper.readValue(content, PersonDTO.class);
		person = updatedPerson;
		
		assertNotNull(updatedPerson);
		
		assertNotNull(updatedPerson.getId());
		assertNotNull(updatedPerson.getName());
		 
		
		assertTrue(updatedPerson.getId() > 0);
		
		assertEquals("Test update", updatedPerson.getName());
	 
	}
	
	@Test
	@Order(3)
	void testFindById() throws JsonMappingException, JsonProcessingException {
		 
		
		var content = given().spec(specification)
				.contentType(Constants.CONTENT_TYPE_JSON)
				.pathParam("id", person.getId())
				.when()
				.get("{id}")
				.then()
				.statusCode(200)
				.extract()
				.body()
				.asString();
		
		final PersonDTO retrievedPerson = objectMapper.readValue(content, PersonDTO.class);
		person = retrievedPerson;
		
		assertNotNull(retrievedPerson);
		
		assertNotNull(retrievedPerson.getId());
		assertNotNull(retrievedPerson.getName());
		 
		
		assertEquals("Test update", retrievedPerson.getName());
	}
	
	@Test
	@Order(4)
	void testFindAll() throws JsonMappingException, JsonProcessingException {
		 
		
		var content = given().spec(specification)
				.contentType(Constants.CONTENT_TYPE_JSON)
				.when()
				.get()
				.then()
				.statusCode(200)
				.extract()
				.body()
				.asString();
		
		final List<PersonDTO> persons = objectMapper.readValue(content, new TypeReference<List<PersonDTO>>(){});
		 
		
		assertNotNull(persons);
		assertTrue(persons.size() > 0);
		assertEquals("Test update", persons.get(0).getName());
	}
	
	
	@Test
	@Order(5)
	void testFindAllWithoutToken() throws JsonMappingException, JsonProcessingException {
		 	
             given()
				.contentType(Constants.CONTENT_TYPE_JSON)
				.basePath("/api/persons")
				.port(Constants.SERVER_PORT)
			    .when()
				.get()
				.then()
				.statusCode(403);
						 
	}
	

	
	
	@Test
	@Order(6)
	void testDelete() throws JsonMappingException, JsonProcessingException {
		 
		
	     given().spec(specification)
				.contentType(Constants.CONTENT_TYPE_JSON)
				.pathParam("id", person.getId())
				.when()
				.delete("{id}")
				.then()
				.statusCode(204);
					 
		
 
	}
	
	private static void mockPerson() {
		 person.setName("Testando 123");
	}

}
