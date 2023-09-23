package com.br.labspringboot3.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.EXPECTATION_FAILED)
public class BusinessLabSpringBoot3Exception extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3063398123254223241L;

	public BusinessLabSpringBoot3Exception(String msg) {
		super(msg);
	}

}
