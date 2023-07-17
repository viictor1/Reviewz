package com.Reviewz.entrypoint.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Reviewz.core.user.usecase.CreateUserUseCase;
import com.Reviewz.core.user.usecase.CreateUserUseCase.Input;

@RestController
@RequestMapping("/auth")
@Tag(name = "User", description = "Endpoints for Managing Users/Account")
public class RegisterController {

	private CreateUserUseCase createUserUseCase;
	
	public RegisterController(CreateUserUseCase createUserUseCase) {
		this.createUserUseCase = createUserUseCase;
	}
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Operation(summary = "Registers an account",
			description = "Registers an account by passing name, login and password",
			tags = {"User"},
			responses = {
					@ApiResponse(description = "Success", responseCode = "200", content = @Content),
					@ApiResponse(description = "Login already being used", responseCode = "409", content = @Content),
					@ApiResponse(description = "Missing value", responseCode = "422", content = @Content)
			}
	)
	public void createUser(@RequestBody Request request) throws Exception {
		createUserUseCase.execute(new Input(request.name, request.login, request.password));
	}
	
	public record Request(
			@NotBlank String name,
			@NotBlank String login,
			@NotBlank String password
		) {}
}
