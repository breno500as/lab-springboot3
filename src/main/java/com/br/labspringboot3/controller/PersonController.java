package com.br.labspringboot3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.labspringboot3.dto.PersonDTO;
import com.br.labspringboot3.service.PersonService;

@RestController
@RequestMapping("persons")
public class PersonController {

	@Autowired
	private PersonService personService;

	@PostMapping
	public ResponseEntity<PersonDTO> create(@RequestBody PersonDTO person) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.personService.save(person));
	}

	@GetMapping
	public ResponseEntity<Iterable<PersonDTO>> findAll() {
		return ResponseEntity.ok().body(this.personService.findAll());
	}

}
