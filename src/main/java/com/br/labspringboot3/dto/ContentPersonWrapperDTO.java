package com.br.labspringboot3.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContentPersonWrapperDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2645437237136252281L;

	@JsonProperty("content")
	private List<PersonDTO> persons;

	public List<PersonDTO> getPersons() {
		return persons;
	}

	public void setPersons(List<PersonDTO> persons) {
		this.persons = persons;
	}

}
