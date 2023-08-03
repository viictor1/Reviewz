package com.Reviewz.infra.dataprovider.database.user;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.Reviewz.core.user.contract.UserGateway;
import com.Reviewz.infra.dataprovider.schema.user.UserSchema;

@Component
public class UserDatabaseGateway implements UserGateway{

	private final UserRepository userRepository;
	
	public UserDatabaseGateway(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public void create(UserSchema user) {
		userRepository.save(user);
	}

	@Override
	public Optional<UserSchema> findOptionalByLogin(String login) {
		return userRepository.findOptionalByLogin(login);
	}

	@Override
	public List<UserSchema> findAll() {
		return userRepository.findAll();
	}

	@Override
	public void delete(UserSchema userSchema) {
		userRepository.delete(userSchema);
	}

	@Override
	public UserSchema update(UserSchema userSchema) { return userRepository.save(userSchema); }

}
