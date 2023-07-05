package com.Reviewz.infrastructure.user.gateway;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.Reviewz.entity.user.gateway.UserGateway;
import com.Reviewz.entity.user.model.User;
import com.Reviewz.infrastructure.config.db.UserRepository;
import com.Reviewz.infrastructure.config.db.schema.UserSchema;

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
	public Optional<User> findByEmail(String email) {
		return userRepository
				.findByEmail(email)
				.map((schema) -> new User(
						schema.getId(),
						schema.getName(),
						schema.getEmail(),
						schema.getPassword()));
	}

}
