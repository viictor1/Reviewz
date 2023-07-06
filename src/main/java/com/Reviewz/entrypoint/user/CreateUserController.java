package com.Reviewz.entrypoint.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Reviewz.core.user.usecase.CreateUserUseCase;
import com.Reviewz.core.user.usecase.CreateUserUseCase.Input;

@RestController
public class CreateUserController {

	private CreateUserUseCase createUserUseCase;
	
	public CreateUserController(CreateUserUseCase createUserUseCase) {
		this.createUserUseCase = createUserUseCase;
	}
	
	@PostMapping("/user")
	@ResponseStatus(HttpStatus.CREATED)
	public void createUser(@RequestBody Request request) throws Exception {
		createUserUseCase.execute(new Input(request.name, request.email, request.password));
	}
	
	private record Request(
			String name,
			String email,
			String password
		) {}
}
