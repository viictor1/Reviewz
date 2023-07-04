package com.Reviewz.infrastructure.user.gateway;

import com.Reviewz.entity.user.gateway.UserGateway;
import com.Reviewz.entity.user.model.User;
import com.victortavin.marmitaria.repositories.user.UserRepository;

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

}
