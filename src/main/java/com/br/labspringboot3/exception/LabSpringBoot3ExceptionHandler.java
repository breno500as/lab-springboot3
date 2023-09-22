package com.br.labspringboot3.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class LabSpringBoot3ExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<LabSpringBoot3Response> handleAllExceptions(Exception e, WebRequest wr) {
		final LabSpringBoot3Response lResponse = new LabSpringBoot3Response(LocalDateTime.now(), e.getMessage(),
				wr.getDescription(false));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(lResponse);

	}

	@ExceptionHandler(ExpectationFailedException.class)
	public final ResponseEntity<LabSpringBoot3Response> handleExpectationFailedException(Exception e, WebRequest wr) {
		final LabSpringBoot3Response lResponse = new LabSpringBoot3Response(LocalDateTime.now(), e.getMessage(),
				wr.getDescription(false));
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(lResponse);

	}

}