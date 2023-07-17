package com.Reviewz.entrypoint.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.Reviewz.core.user.exception.ValidationError;
import com.Reviewz.core.user.usecase.DeleteAccountUseCase;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/user")
public class DeleteAccountController {

	private DeleteAccountUseCase deleteAccountUseCase;
	
	public DeleteAccountController(DeleteAccountUseCase deleteAccountUseCase) {
		this.deleteAccountUseCase = deleteAccountUseCase;
	}
	
	@DeleteMapping
	@SecurityRequirement(name = "bearerAuth")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAccount(@RequestHeader (name="Authorization") String token, @RequestBody Request request) throws ValidationError {
		deleteAccountUseCase.execute(new DeleteAccountUseCase.Input(
				token.replace("Bearer ", ""),
				request.password)
			);
	}
	
	private record Request(String password) {}
	
	
}
