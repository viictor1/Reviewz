package com.Reviewz.core.user.usecase;

import com.Reviewz.core.genericException.ValidationError;
import com.Reviewz.core.user.contract.UserGateway;
import com.Reviewz.infra.dataprovider.schema.user.UserSchema;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.Reviewz.core.user.utils.UserUtils.retrieveUserFromToken;

@Service
public class DeleteAccountUseCase {
	
	private UserGateway userGateway;
	
	private PasswordEncoder passwordEncoder;
	
	public DeleteAccountUseCase(UserGateway userGateway, PasswordEncoder passwordEncoder) {
		this.userGateway = userGateway;
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
