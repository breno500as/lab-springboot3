package com.br.labspringboot3.integrationtests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.br.labspringboot3.entity.PersonEntity;
import com.br.labspringboot3.repository.PersonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Não vai aterar nada no banco
@TestMethodOrder(OrderAnnotation.class)
class PersonRepositoryTest extends AbstractIntegrationTest {

	@Autowired
	public PersonRepository repository;

	@Test
	@Order(1)
	void testFindByName() throws JsonMappingException, JsonProcessingException {

		Pageable pageable = PageRequest.of(0, 6, Sort.by(Direction.ASC, "name"));
		final List<PersonEntity> persons = repository.findAll(pageable).getContent();

		assertNotNull(persons);

		assertEquals(0, persons.size());

	}

}
