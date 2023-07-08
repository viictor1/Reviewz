package com.Reviewz.core.user.usecase;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Reviewz.core.user.contract.UserGateway;
import com.Reviewz.core.user.exception.EmailAlreadyExistsException;
import com.Reviewz.core.user.model.User;

@Service
public class CreateUserUseCase {

	private UserGateway userGateway;
	
	public CreateUserUseCase(UserGateway userGateway) {
		this.userGateway = userGateway;
	}
	
	public void execute(Input input) throws Exception {
		
		if(checkIfEmailUsed(input.email)) {
			throw new EmailAlreadyExistsException("Email ja utilizado");
		}
		
		User user = setUserFromInput(input);
		
		userGateway.create(user);
	}
	
	public User setUserFromInput(Input input) throws Exception {
		User user = new User();
		
		user.setName(input.name);
		user.setEmail(input.email);
		user.setPassword(input.password);
		
		return user;
	}
	
	public boolean checkIfEmailUsed(String email) {
		Optional<User> optionalUser = userGateway.findOptionalByEmail(email);
		
		if(optionalUser.isEmpty()) {
			return false;
		}
		
		return true;
	}
	
	public record Input(String name, String email, String password) { }
}
