package com.Reviewz.entrypoint.user;

import com.Reviewz.core.authentication.usecase.TokenService;
import com.Reviewz.infra.dataprovider.schema.user.UserSchema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "User", description = "Endpoints for Managing Users/Account")
public class LoginController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;

	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Retrieves a JWT token",
			description = "Retrieves a JWT token by passing login, and password in request body",
			tags = {"User"},
			responses = {
					@ApiResponse(description = "Success", responseCode = "200", content = @Content),
					@ApiResponse(description = "User not found or incorrect password", responseCode = "403", content = @Content)
			}
	)
	public Response login(@RequestBody @Valid Input input) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(input.login, input.password);
		var auth = this.authenticationManager.authenticate(usernamePassword);
		
		var token = tokenService.generateToken((UserSchema) auth.getPrincipal());
		
		return new Response(token);
	}

	@Schema(hidden = true, name = "Login Request")
	private record Input(@NotBlank String login, @NotBlank String password) {};
	private record Response(String token) {};
}
