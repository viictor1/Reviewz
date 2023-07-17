package com.Reviewz.entrypoint.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Reviewz.core.user.exception.ValidationError;
import com.Reviewz.core.user.model.User;
import com.Reviewz.core.user.usecase.GetUserByLoginUseCase;
import com.Reviewz.infra.dataprovider.schema.user.UserRole;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "Endpoints for Managing Users/Account")
public class GetUserByLoginController {
	
	private GetUserByLoginUseCase getUserByLoginUseCase;

	public GetUserByLoginController(GetUserByLoginUseCase getUserByLoginUseCase) {
		this.getUserByLoginUseCase = getUserByLoginUseCase;
	}

	@SecurityRequirement(name = "bearerAuth")
	@GetMapping(value = "/{login}")
	public Response getUserByLogin(@PathVariable String login) throws ValidationError {
		User user = getUserByLoginUseCase.execute(login);
		return new Response(user.getName(), user.getLogin(), user.getRole());
	}
	
	record Response(String name, String login, UserRole role) {}
}
