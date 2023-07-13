package com.Reviewz.core.user.usecase;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Reviewz.core.user.contract.UserGateway;
import com.Reviewz.core.user.exception.ValidationError;
import com.Reviewz.core.user.model.User;

import jakarta.transaction.Transactional;

@Service
public class GetUserByLoginUseCase {

	private UserGateway userGateway;
	
	public GetUserByLoginUseCase(UserGateway userGateway) {
		this.userGateway = userGateway;
	}
	
	@Transactional
	public User getUserByLogin(String login) throws ValidationError {
		Optional<User> optionalUser = userGateway.findOptionalByLogin(login);
		if(optionalUser.isEmpty()) {
			throw new ValidationError("User not found");
		}
		else {
			return optionalUser.get();
		}
	}
}
