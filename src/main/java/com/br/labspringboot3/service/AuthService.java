package com.br.labspringboot3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.br.labspringboot3.dto.AccountCredentialsDTO;
import com.br.labspringboot3.dto.TokenDTO;
import com.br.labspringboot3.repository.UserRepository;
import com.br.labspringboot3.security.JwtTokenProvider;

@Service
public class AuthService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private UserRepository repository;

	public TokenDTO login(AccountCredentialsDTO data) {
		try {
			var username = data.getUsername();
			var pasword = data.getPassword();

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, pasword));

			var user = repository.findByUsername(username);

			if (user != null) {
				return tokenProvider.createAccessToken(username, user.getRoles());
			}
			throw new UsernameNotFoundException("Username " + username + " not found!");

		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Invalid username/password supplied!");
		}
	}

	public TokenDTO refreshToken(String username, String refreshToken) {
		var user = repository.findByUsername(username);

		var tokenResponse = new TokenDTO();
		if (user != null) {
			tokenResponse = tokenProvider.refreshToken(refreshToken);
		} else {
			throw new UsernameNotFoundException("Username " + username + " not found!");
		}
		return tokenResponse;
	}

}
