package com.Reviewz.entrypoint.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("/auth")
public class LoginController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public Response login(@RequestBody @Valid Input input) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(input.login, input.password);
		var auth = this.authenticationManager.authenticate(usernamePassword);
		
		return new Response("parece que deu certo");
	}
	
	private record Input(@NotBlank String login, @NotBlank String password) {};
	private record Response(String token) {};
}
