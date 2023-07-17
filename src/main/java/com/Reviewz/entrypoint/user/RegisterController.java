package com.Reviewz.entrypoint.user;

import io.swagger.v3.oas.annotations.tags.Tag;
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
	public void createUser(@RequestBody Request request) throws Exception {
		createUserUseCase.execute(new Input(request.name, request.login, request.password));
	}
	
	public record Request(
			String name,
			String login,
			String password
		) {}
}
