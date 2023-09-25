package com.br.labspringboot3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.labspringboot3.dto.AccountCredentialsDTO;
import com.br.labspringboot3.dto.TokenDTO;
import com.br.labspringboot3.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "AuthenticationEndpoint")
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@Operation(description = "Authenticates a user and returns a token")

	@PostMapping(value = "/signin")
	public ResponseEntity<TokenDTO> login(@RequestBody AccountCredentialsDTO data) {
		return ResponseEntity.ok(this.authService.login(data));
	}

}
