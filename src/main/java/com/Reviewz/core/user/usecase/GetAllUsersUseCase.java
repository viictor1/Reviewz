package com.Reviewz.core.user.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Reviewz.core.user.contract.UserGateway;
import com.Reviewz.core.user.model.User;

import jakarta.transaction.Transactional;

@Service
public class GetAllUsersUseCase {
 
	private UserGateway userGateway;
	
	public GetAllUsersUseCase(UserGateway userGateway) {
		this.userGateway = userGateway;
	}
	
	@Transactional
	public List<User> getAllUsers(){
		return userGateway.findAll();
	}
}
