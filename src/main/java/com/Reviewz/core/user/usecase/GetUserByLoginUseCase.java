package com.Reviewz.core.user.usecase;

import org.springframework.stereotype.Service;

import com.Reviewz.core.user.contract.UserGateway;
import com.Reviewz.core.genericException.ValidationError;
import com.Reviewz.core.user.model.User;
import com.Reviewz.infra.dataprovider.schema.user.UserSchema;

@Service
public class GetUserByLoginUseCase {

	private UserGateway userGateway;
	
	public GetUserByLoginUseCase(UserGateway userGateway) {
		this.userGateway = userGateway;
	}

	public User execute(String login) throws ValidationError {
		UserSchema userSchema = userGateway.findOptionalByLogin(login)
				.orElseThrow(() -> new ValidationError("User not found"));
		
		return new User(userSchema);

	}
}
