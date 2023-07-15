package com.Reviewz.core.user.usecase;

import com.Reviewz.core.user.contract.UserGateway;
import com.Reviewz.core.user.model.User;
import com.Reviewz.infra.dataprovider.schema.user.UserSchema;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetAllUsersUseCase {
 
	private UserGateway userGateway;
	
	public GetAllUsersUseCase(UserGateway userGateway) {
		this.userGateway = userGateway;
	}

	public List<User> execute(){
		List<UserSchema> schemaList = userGateway.findAll();
		List<User> userList = new ArrayList<User>();
		
		for (UserSchema userSchema : schemaList) {
			userList.add(new User(userSchema));
		}
		
		return userList;
	}
}
