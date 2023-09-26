package com.br.labspringboot3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.labspringboot3.dto.PersonDTO;
import com.br.labspringboot3.service.PersonService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/persons")
@Tag(name = "Person", description = "API for managing persons")
public class PersonController {

	@Autowired
	private PersonService personService;

	@PostMapping
	@Operation(summary = "Create person", description = "Create person", tags = "Person", responses = {
			@ApiResponse(responseCode = "201", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class)) }),
			@ApiResponse(responseCode = "417", content = @Content),
			@ApiResponse(responseCode = "500", content = @Content)

	})
	public ResponseEntity<PersonDTO> create(@RequestBody PersonDTO person) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.personService.save(person));
	}

	@PutMapping
	@Operation(summary = "Update person", description = "Update person", tags = "Person", responses = {
			@ApiResponse(responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class)) }),
			@ApiResponse(responseCode = "404", content = @Content),
			@ApiResponse(responseCode = "417", content = @Content),
			@ApiResponse(responseCode = "500", content = @Content)

	})
	public ResponseEntity<PersonDTO> update(@RequestBody PersonDTO person) {
		return ResponseEntity.ok().body(this.personService.update(person));
	}

	@GetMapping("{id}")
	@Operation(summary = "Find person by id", description = "Find person by id", tags = "Person", responses = {
			@ApiResponse(responseCode = "200", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class)) }),
			@ApiResponse(responseCode = "404", content = @Content),
			@ApiResponse(responseCode = "500", content = @Content)

	})
	public ResponseEntity<PersonDTO> findById(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok().body(this.personService.findById(id));
	}

	@GetMapping(value = "/v2")
	public ResponseEntity<Iterable<PersonDTO>> findAllV2() {
		return ResponseEntity.ok().body(this.personService.findAll(null));
	}

	@GetMapping
	@Operation(summary = "Find all persons", description = "Find all persons", tags = "Person", responses = {
			@ApiResponse(responseCode = "200", content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class))) }),
			@ApiResponse(responseCode = "500", content = @Content)

	})
	public ResponseEntity<Page<PersonDTO>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "12") Integer size,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		
		var sortDirection = "desc".equalsIgnoreCase(direction)
				? Direction.DESC : Direction.ASC;
		
		
		return ResponseEntity.ok().body(this.personService.findAll(PageRequest.of(page, size, Sort.by(sortDirection, "name"))));
	}

	@DeleteMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Delete person", description = "Delete person", tags = "Person", responses = {
			@ApiResponse(responseCode = "204", content = @Content),
			@ApiResponse(responseCode = "404", content = @Content),
			@ApiResponse(responseCode = "500", content = @Content)

	})
	public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
		this.personService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
