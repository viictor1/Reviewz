package com.Reviewz.entrypoint.user;

import com.Reviewz.core.authentication.usecase.TokenService;
import com.Reviewz.infra.dataprovider.schema.user.UserSchema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "User", description = "Endpoints for Managing Users/Account")
public class LoginController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;

	@PostMapping("/login")
	public Response login(@RequestBody @Valid Input input) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(input.login, input.password);
		var auth = this.authenticationManager.authenticate(usernamePassword);
		
		var token = tokenService.generateToken((UserSchema) auth.getPrincipal());
		
		return new Response(token);
	}
	
	private record Input(@NotBlank String login, @NotBlank String password) {};
	private record Response(String token) {};
}
