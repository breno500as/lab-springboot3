package com.br.labspringboot3.dto;

public class PersonDTO {

	private Long id;

	private String name;

	public PersonDTO(String name) {
		super();
		this.name = name;
	}

	public PersonDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public PersonDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
