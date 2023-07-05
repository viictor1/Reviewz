package com.Reviewz.useCase.User;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Reviewz.entity.user.gateway.UserGateway;
import com.Reviewz.entity.user.model.User;

@Service
public class SearchUserByEmailUseCase {
	private UserGateway userGateway;
	
	public SearchUserByEmailUseCase(UserGateway userGateway) {
		this.userGateway = userGateway;
	}
	
	public Output execute(Input input) {
		return new Output(userGateway.findByEmail(input.email));
	}
	
	public record Input(String email) {}
	public record Output(Optional<User> optionalUser) {}

}
