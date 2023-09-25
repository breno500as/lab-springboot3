package com.br.labspringboot3.exception.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.br.labspringboot3.exception.BusinessLabSpringBoot3Exception;
import com.br.labspringboot3.exception.InvalidJwtAuthenticationException;
import com.br.labspringboot3.exception.LabSpringBoot3Response;
import com.br.labspringboot3.exception.NotFoundException;

@RestController
@ControllerAdvice
public class LabSpringBoot3ExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<LabSpringBoot3Response> handleAllExceptions(Exception e, WebRequest wr) {
		final LabSpringBoot3Response lResponse = new LabSpringBoot3Response(LocalDateTime.now(), e.getMessage(),
				wr.getDescription(false));
		return ResponseEntity.internalServerError().body(lResponse);

	}

	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<LabSpringBoot3Response> handleNotFoundException(Exception e, WebRequest wr) {
		final LabSpringBoot3Response lResponse = new LabSpringBoot3Response(LocalDateTime.now(), e.getMessage(),
				wr.getDescription(false));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(lResponse);

	}

	@ExceptionHandler(BusinessLabSpringBoot3Exception.class)
	public final ResponseEntity<LabSpringBoot3Response> handleBussinessException(Exception e, WebRequest wr) {
		final LabSpringBoot3Response lResponse = new LabSpringBoot3Response(LocalDateTime.now(), e.getMessage(),
				wr.getDescription(false));
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(lResponse);

	}
	
	@ExceptionHandler(InvalidJwtAuthenticationException.class)
	public final ResponseEntity<LabSpringBoot3Response> handleInvalidJwtAuthenticationException(Exception e, WebRequest wr) {
		final LabSpringBoot3Response lResponse = new LabSpringBoot3Response(LocalDateTime.now(), e.getMessage(),
				wr.getDescription(false));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(lResponse);

	}
	
 

}
