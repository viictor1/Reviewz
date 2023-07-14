package com.Reviewz.core.user.usecase;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.Reviewz.core.user.contract.UserGateway;
import com.Reviewz.core.user.model.User;
import com.Reviewz.infra.dataprovider.schema.user.UserSchema;

import jakarta.transaction.Transactional;

@Service
public class GetAllUsersUseCase {
 
	private UserGateway userGateway;
	
	public GetAllUsersUseCase(UserGateway userGateway) {
		this.userGateway = userGateway;
	}
	
	@Transactional
	public List<User> getAllUsers(){
		List<UserSchema> schemaList = userGateway.findAll();
		List<User> userList = new ArrayList<User>();
		
		for (UserSchema userSchema : schemaList) {
			userList.add(new User(userSchema));
		}
		
		return userList;
	}
}
