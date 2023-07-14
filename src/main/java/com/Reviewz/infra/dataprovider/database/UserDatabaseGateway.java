package com.Reviewz.infra.dataprovider.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.Reviewz.core.user.contract.UserGateway;
import com.Reviewz.core.user.model.User;
import com.Reviewz.infra.dataprovider.schema.user.UserSchema;

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

	@Override
	public List<User> findAll() {
		List<UserSchema> userSchemaList = userRepository.findAll();
		List<User> userList = new ArrayList<User>();
		
		for (UserSchema user : userSchemaList) {
			userList.add(new User(user));
		}
		
		return userList;
	}

}
