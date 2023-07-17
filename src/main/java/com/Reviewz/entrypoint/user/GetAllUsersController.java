package com.Reviewz.entrypoint.user;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Reviewz.core.user.model.User;
import com.Reviewz.core.user.usecase.GetAllUsersUseCase;
import com.Reviewz.infra.dataprovider.schema.user.UserRole;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "Endpoints for Managing Users/Account")
public class GetAllUsersController {
	
	private GetAllUsersUseCase getAllUsersUseCase;

	public GetAllUsersController(GetAllUsersUseCase getAllUsersUseCase) {
		this.getAllUsersUseCase = getAllUsersUseCase;
	}
	
	@SecurityRequirement(name = "bearerAuth")
	@GetMapping
	public List<Response> getAllUsers() {
		List<User> userList = getAllUsersUseCase.execute();
		List<Response> responseList = new ArrayList<Response>();
		
		for (User user : userList) {
			responseList.add(new Response(
					user.getName(),
					user.getLogin(),
					user.getRole()));
		}
		
		return responseList;
	}
	
	record Response(String name, String login, UserRole role) {}
}
