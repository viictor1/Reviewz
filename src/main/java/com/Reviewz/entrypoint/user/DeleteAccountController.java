package com.Reviewz.entrypoint.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
	public void deleteAccount(@RequestHeader (name="Authorization") String token, Input input) throws ValidationError {
		deleteAccountUseCase.deleteAccount(token.replace("Bearer ", ""), input.password);
	}
	
	private record Input(String password) {}
	
	
}
