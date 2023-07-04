package com.Reviewz.useCase.User;

import org.springframework.stereotype.Service;

import com.Reviewz.entity.user.gateway.UserGateway;
import com.Reviewz.entity.user.model.User;

@Service
public class CreateUserUseCase {

	private UserGateway userGateway;
	
	public CreateUserUseCase(UserGateway userGateway) {
		this.userGateway = userGateway;
	}
	
	public void execute(Input input) {
		var user = new User(input.name(), input.email(), input.password());
		userGateway.create(user);
	}
	
	public record Input(String name, String email, String password) { }
}
