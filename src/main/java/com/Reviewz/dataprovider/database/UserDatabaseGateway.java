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
				user.getLogin(),
				user.getPassword(),
				user.getRole()));
	}

	@Override
	public Optional<User> findOptionalByLogin(String login) {
		return userRepository
				.findOptionalByLogin(login)
				.map((schema) -> new User(
						schema.getId(),
						schema.getName(),
						schema.getLogin(),
						schema.getPassword(),
						schema.getRole()));
	}

}
