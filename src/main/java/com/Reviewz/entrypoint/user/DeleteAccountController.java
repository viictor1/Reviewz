package com.Reviewz.entrypoint.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.Reviewz.core.user.exception.ValidationError;
import com.Reviewz.core.user.usecase.DeleteAccountUseCase;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "Endpoints for Managing Users/Account")
public class DeleteAccountController {

	private DeleteAccountUseCase deleteAccountUseCase;
	
	public DeleteAccountController(DeleteAccountUseCase deleteAccountUseCase) {
		this.deleteAccountUseCase = deleteAccountUseCase;
	}
	
	@DeleteMapping
	@SecurityRequirement(name = "bearerAuth")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Operation(summary = "Deletes an Account",
			description = "Deletes an account by passing the user's password while logged in",
			tags = {"User"},
			responses = {
					@ApiResponse(description = "Success", responseCode = "204", content = @Content),
					@ApiResponse(description = "User not found or incorrect password", responseCode = "422", content = @Content),
					@ApiResponse(description = "User not logged in", responseCode = "403", content = @Content),
					@ApiResponse(description = "Missing value", responseCode = "422", content = @Content)

			})
	public void deleteAccount(@RequestHeader (name="Authorization") String token, @RequestBody Request request) throws ValidationError {
		deleteAccountUseCase.execute(new DeleteAccountUseCase.Input(
				token,
				request.password)
			);
	}

	@Schema(hidden = true, name = "Delete Request" )
	private record Request(@NotBlank String password) {}
	
	
}
