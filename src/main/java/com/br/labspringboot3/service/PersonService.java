package com.br.labspringboot3.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.labspringboot3.dto.PersonDTO;
import com.br.labspringboot3.entity.PersonEntity;
import com.br.labspringboot3.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	private Logger log = LoggerFactory.getLogger(PersonService.class);

	public PersonDTO save(PersonDTO person) {
		this.log.info("save person");
		final PersonEntity personEntity = this.personRepository.save(new PersonEntity(person.getName()));
		return new PersonDTO(personEntity.getId(), personEntity.getName());
	}

	public List<PersonDTO> findAll() {
		this.log.info("find all persons");
		return StreamSupport.stream(this.personRepository.findAll().spliterator(), false)
				.map(p -> new PersonDTO(p.getId(), p.getName())).toList();

	}

}
