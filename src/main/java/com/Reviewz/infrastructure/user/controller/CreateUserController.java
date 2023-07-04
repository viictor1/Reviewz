package com.Reviewz.infrastructure.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Reviewz.useCase.User.CreateUserUseCase;
import com.Reviewz.useCase.User.CreateUserUseCase.Input;

@RestController
public class CreateUserController {

	private CreateUserUseCase createUserUseCase;
	
	public CreateUserController(CreateUserUseCase createUserUseCase) {
		this.createUserUseCase = createUserUseCase;
	}
	
	@PostMapping("/user")
	@ResponseStatus(HttpStatus.CREATED)
	public void createUser(@RequestBody Request request) {
		createUserUseCase.execute(new Input(request.name, request.email, request.password));
	}
	
	private record Request(
			String name,
			String email,
			String password
		) {}
}
