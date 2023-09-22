package com.br.labspringboot3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.br.labspringboot3.entity.Person;
import com.br.labspringboot3.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	@PostMapping
	public Person save(Person person) {
		return this.personRepository.save(person);
	}

}
