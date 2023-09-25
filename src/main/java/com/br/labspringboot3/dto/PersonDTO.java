package com.br.labspringboot3.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;

public class PersonDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 842882287900088600L;

	private Long id;

	@NotNull
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
