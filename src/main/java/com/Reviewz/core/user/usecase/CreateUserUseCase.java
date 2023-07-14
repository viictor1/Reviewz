package com.Reviewz.core.user.usecase;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Reviewz.core.user.contract.UserGateway;
import com.Reviewz.core.user.exception.LoginAlreadyExistsException;
import com.Reviewz.core.user.model.User;
import com.Reviewz.infra.dataprovider.schema.user.UserRole;
import com.Reviewz.infra.dataprovider.schema.user.UserSchema;

@Service
public class CreateUserUseCase {

	private final UserGateway userGateway;
	
	private final PasswordEncoder passwordEncoder;
	
	public CreateUserUseCase(UserGateway userGateway, PasswordEncoder passwordEncoder) {
		this.userGateway = userGateway;
		this.passwordEncoder = passwordEncoder;
	}
	
	public void execute(Input input) throws Exception {
		
		if(checkIfLoginUsed(input.email)) {
			throw new LoginAlreadyExistsException("Login already being used");
		}
		
		User user = setUserFromInput(input);
		
		userGateway.create(new UserSchema(user));
	}
	
	public User setUserFromInput(Input input) throws Exception {
		User user = new User();
		
		user.setName(input.name);
		user.setLogin(input.email);
		user.setPassword(passwordEncoder.encode(input.password));
		user.setRole(UserRole.USER);
		
		return user;
	}
	
	public boolean checkIfLoginUsed(String email) {
		Optional<UserSchema> optionalUser = userGateway.findOptionalByLogin(email);

		return optionalUser.isPresent();
	}
	
	public record Input(String name, String email, String password) { }
}
