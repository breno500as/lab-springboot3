package com.br.labspringboot3.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.labspringboot3.dto.PersonDTO;
import com.br.labspringboot3.entity.PersonEntity;
import com.br.labspringboot3.exception.BusinessLabSpringBoot3Exception;
import com.br.labspringboot3.exception.NotFoundException;
import com.br.labspringboot3.repository.PersonRepository;
import com.br.labspringboot3.util.LabSpringBoot3Messages;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	private Logger log = LoggerFactory.getLogger(PersonService.class);

	public PersonDTO save(PersonDTO person) {
		
		if (person == null) {
			throw new BusinessLabSpringBoot3Exception(LabSpringBoot3Messages.MSG_01);
		}
		
		this.log.info("save person");

		final PersonEntity personEntity = this.personRepository.save(new PersonEntity(person.getName()));
		
		return new PersonDTO(personEntity.getId(), personEntity.getName());
	}

	public PersonDTO update(PersonDTO person) {
		
		if (person == null || person.getId() == null) {
			throw new BusinessLabSpringBoot3Exception(LabSpringBoot3Messages.MSG_01);
		}

		this.log.info("update person");

		final PersonEntity pEntity = this.findEntityById(person.getId());

		pEntity.setName(person.getName());

		final PersonEntity updatedPerson = this.personRepository.save(pEntity);

		return new PersonDTO(updatedPerson.getId(), updatedPerson.getName());

	}

	public List<PersonDTO> findAll() {
		
		this.log.info("find all persons");
		
		return StreamSupport.stream(this.personRepository.findAll().spliterator(), false)
				.map(p -> new PersonDTO(p.getId(), p.getName())).toList();

	}

	public void delete(Long id) {
		this.log.info("delete person");

		this.personRepository.deleteById(id);
	}

	private PersonEntity findEntityById(Long id) {
		return this.personRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("person not found!"));
	}

	public PersonDTO findById(Long id) {

		this.log.info("find person by id");

		final PersonEntity p = this.findEntityById(id);

		return new PersonDTO(p.getId(), p.getName());
	}

}
