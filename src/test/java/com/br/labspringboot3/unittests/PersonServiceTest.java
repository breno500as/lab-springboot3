package com.br.labspringboot3.unittests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.br.labspringboot3.dto.PersonDTO;
import com.br.labspringboot3.entity.PersonEntity;
import com.br.labspringboot3.exception.BusinessLabSpringBoot3Exception;
import com.br.labspringboot3.repository.PersonRepository;
import com.br.labspringboot3.service.PersonService;
import com.br.labspringboot3.util.LabSpringBoot3Messages;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

	private static final String PERSON_TEST_NAME = "test name";

	private static final String PERSON_NEW_TEST_NAME = "new test name";

	private static final Long PERSON_TEST_ID = 1L;

	@InjectMocks
	private PersonService personService;

	@Mock
	private PersonRepository personRepository;

	@BeforeEach
	void setUp() throws Exception {

		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testSave() {

		final PersonEntity p = new PersonEntity();
		p.setName(PERSON_TEST_NAME);

		final PersonEntity persisted = new PersonEntity();
		persisted.setId(PERSON_TEST_ID);
		persisted.setName(PERSON_TEST_NAME);

		when(this.personRepository.save(p)).thenReturn(persisted);

		var result = this.personService.save(new PersonDTO(PERSON_TEST_NAME));

		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());
		assertEquals(PERSON_TEST_ID, result.getId());
		assertEquals(PERSON_TEST_NAME, result.getName());

	}

	@Test
	void testUpdate() {

		final PersonEntity p = new PersonEntity();
		p.setId(PERSON_TEST_ID);
		p.setName(PERSON_NEW_TEST_NAME);

		final PersonEntity persisted = new PersonEntity();
		persisted.setId(PERSON_TEST_ID);
		persisted.setName(PERSON_NEW_TEST_NAME);

		when(this.personRepository.findById(PERSON_TEST_ID)).thenReturn(Optional.of(p));
		when(this.personRepository.save(p)).thenReturn(persisted);

		var result = this.personService.update(new PersonDTO(PERSON_TEST_ID, PERSON_NEW_TEST_NAME));

		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());
		assertEquals(PERSON_TEST_ID, result.getId());
		assertEquals(PERSON_NEW_TEST_NAME, result.getName());
	}

	@Test
	void testFindAll() {
		
		
		final List<PersonEntity> persons = new ArrayList<>();
		persons.add(new PersonEntity(1L, "a"));
		persons.add(new PersonEntity(2L, "b"));
		persons.add(new PersonEntity(3L, "c"));
		
		var pageable = PageRequest.of(0, 10, Sort.by("asc", "name"));

		when(this.personRepository.findAll(pageable)).thenReturn(new PageImpl<PersonEntity>(persons));

		var result = this.personService.findAll(pageable);

		assertNotNull(result);
		assertNotNull(result.getContent());
		assertEquals(3, result.getContent().size());

		var p1 = result.getContent().get(1);
		assertEquals(2, p1.getId());
		assertEquals("b", p1.getName());

	}

	@Test
	void testSaveBussinessRule() {

		Exception e = assertThrows(BusinessLabSpringBoot3Exception.class, () -> {
			this.personService.save(null);
		});

		assertEquals(LabSpringBoot3Messages.MSG_01, e.getMessage());
	}

	@Test
	void testDelete() {
		this.personService.delete(PERSON_TEST_ID);
	}

	@Test
	void testFindById() {

		final PersonEntity p = new PersonEntity();
		p.setId(PERSON_TEST_ID);
		p.setName(PERSON_TEST_NAME);

		when(this.personRepository.findById(PERSON_TEST_ID)).thenReturn(Optional.of(p));

		var result = this.personService.findById(PERSON_TEST_ID);

		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getName());
		assertEquals(PERSON_TEST_ID, result.getId());
		assertEquals(PERSON_TEST_NAME, result.getName());

	}

}
