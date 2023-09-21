package com.br.labspringboot3.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.EXPECTATION_FAILED)
public class ExpectationFailedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5467424824208697951L;

}
