package com.Reviewz.core.user.usecase;

import com.Reviewz.core.authentication.usecase.TokenService;
import com.Reviewz.core.user.contract.UserGateway;
import com.Reviewz.core.user.genericException.ValidationError;
import com.Reviewz.infra.dataprovider.schema.user.UserSchema;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DeleteAccountUseCase {
	
	private UserGateway userGateway;
	
	private TokenService tokenService;
	
	private PasswordEncoder passwordEncoder;
	
	public DeleteAccountUseCase(UserGateway userGateway, TokenService tokenService, PasswordEncoder passwordEncoder) {
		this.userGateway = userGateway;
		this.tokenService = tokenService;
		this.passwordEncoder = passwordEncoder;
	}
	
	public void execute(Input input) throws ValidationError {
		UserSchema user = retrieveUserFromToken(input.token.replace("Bearer ", ""));
		
		if (typedPasswordEqualsActualPassword(input.password, user.getPassword())) {
			userGateway.delete(user);			
		}
		else {
			incorrectPassword();
		}
		
	}
	
	private UserSchema retrieveUserFromToken(String token) throws ValidationError {
		String login = tokenService.validateToken(token);
		
		return userGateway.findOptionalByLogin(login)
				.orElseThrow(() -> new ValidationError("User not found"));
	}
	
	private boolean typedPasswordEqualsActualPassword(String password, String userPassword) {
		return passwordEncoder.matches(password, userPassword);
	}
	
	private void incorrectPassword() throws ValidationError {
		throw new ValidationError("Incorrect password");
	}

	public record Input(
			String token,
			String password
	){}

}
