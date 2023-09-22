package com.br.labspringboot3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@PutMapping
	public ResponseEntity<PersonDTO> update(@RequestBody PersonDTO person) {
		return ResponseEntity.ok().body(this.personService.update(person));
	}
	
	@GetMapping("{id}")
	public ResponseEntity<PersonDTO> findById(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok().body(this.personService.findById(id));
	}

	@GetMapping
	public ResponseEntity<Iterable<PersonDTO>> findAll() {
		return ResponseEntity.ok().body(this.personService.findAll());
	}

	@DeleteMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
		this.personService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
