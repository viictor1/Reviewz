package com.Reviewz.useCase.User;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Reviewz.entity.user.gateway.UserGateway;
import com.Reviewz.entity.user.model.User;

@Service
public class CreateUserUseCase {

	private UserGateway userGateway;
	
	public CreateUserUseCase(UserGateway userGateway) {
		this.userGateway = userGateway;
	}
	
	public void execute(Input input) throws Exception {
		User user = new User();
		if(checkIfEmailUsed(input.email)) {
			throw new Exception("Email ja utilizado");
		}
		user.setName(input.name);
		user.setEmail(input.email);
		user.setPassword(input.password);
		userGateway.create(user);
	}
	
	public boolean checkIfEmailUsed(String email) {
		Optional<User> optionalUser = userGateway.findByEmail(email);
		
		if(optionalUser.isEmpty()) {
			return false;
		}
		
		return true;
	}
	
	public record Input(String name, String email, String password) { }
}
