package com.br.labspringboot3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.labspringboot3.entity.Person;
import com.br.labspringboot3.repository.PersonRepository;

@RestController
@RequestMapping("persons")
public class PersonController {

	@Autowired
	private PersonRepository personRepository;

	@PostMapping
	public ResponseEntity<Person> create() {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.personRepository.save(new Person("brahminha")));

	}

}
