package com.Reviewz.core.user.usecase;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Reviewz.core.user.contract.UserGateway;
import com.Reviewz.core.user.exception.EmailAlreadyExistsException;
import com.Reviewz.core.user.model.User;
import com.Reviewz.dataprovider.schema.UserRole;

@Service
public class CreateUserUseCase {

	private UserGateway userGateway;
	
	private BCryptPasswordEncoder passwordEncoder;
	
	public CreateUserUseCase(UserGateway userGateway, BCryptPasswordEncoder passwordEncoder) {
		this.userGateway = userGateway;
		this.passwordEncoder = passwordEncoder;
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
		user.setPassword(passwordEncoder.encode(input.password));
		user.setRole(UserRole.USER);
		
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
