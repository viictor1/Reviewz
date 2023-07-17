package com.Reviewz.entrypoint.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Gets a user by login",
			description = "Gets a user by passing his login",
			tags = {"User"},
			responses = {
					@ApiResponse(description = "Success", responseCode = "200", content = @Content),
					@ApiResponse(description = "User isn't an admin", responseCode = "403", content = @Content),
					@ApiResponse(description = "User not found", responseCode = "422", content = @Content)
			}
	)
	public Response getUserByLogin(@PathVariable String login) throws ValidationError {
		User user = getUserByLoginUseCase.execute(login);
		return new Response(user.getName(), user.getLogin(), user.getRole());
	}
	
	record Response(String name, String login, UserRole role) {}
}
