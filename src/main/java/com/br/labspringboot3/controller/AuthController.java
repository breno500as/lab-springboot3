package com.br.labspringboot3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.labspringboot3.dto.AccountCredentialsDTO;
import com.br.labspringboot3.dto.TokenDTO;
import com.br.labspringboot3.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/auth")
@Tag(name = "AuthenticationEndpoint")
public class AuthController {

	@Autowired
	private AuthService authService;


	@PostMapping(value = "/signin")
	@Operation(description = "Authenticates a user and returns a token")
	public ResponseEntity<TokenDTO> login(@RequestBody AccountCredentialsDTO data) {
		return ResponseEntity.ok(this.authService.login(data));
	}
	
	
	@PutMapping(value = "/refresh-token/{userName}")
	@Operation(description = "Update refresh token and return")
	public ResponseEntity<TokenDTO> refreshToken(@PathVariable("userName") String userName, @RequestHeader("Authorization") String refreshToken) {
		return ResponseEntity.ok(this.authService.refreshToken(userName, refreshToken));
	}
	
	
	

}
