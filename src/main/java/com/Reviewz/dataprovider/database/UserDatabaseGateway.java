package com.Reviewz.dataprovider.database;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.Reviewz.core.user.contract.UserGateway;
import com.Reviewz.core.user.model.User;
import com.Reviewz.dataprovider.schema.UserSchema;

@Component
public class UserDatabaseGateway implements UserGateway{

	private UserRepository userRepository;
	
	public UserDatabaseGateway(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public void create(User user) {
		userRepository.save(new UserSchema(
				null, 
				user.getName(),
				user.getEmail(),
				user.getPassword()));
	}

	@Override
	public Optional<User> findOptionalByEmail(String email) {
		return userRepository
				.findOptionalByEmail(email)
				.map((schema) -> new User(
						schema.getId(),
						schema.getName(),
						schema.getEmail(),
						schema.getPassword(),
						schema.getRole()));
	}

}
