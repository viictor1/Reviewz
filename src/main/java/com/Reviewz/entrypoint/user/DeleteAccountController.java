package com.Reviewz.entrypoint.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Reviewz.core.user.usecase.DeleteAccountUseCase;

@RestController
@RequestMapping("/auth")
public class DeleteAccountController {

	private DeleteAccountUseCase deleteAccountUseCase;
	
	public DeleteAccountController(DeleteAccountUseCase deleteAccountUseCase) {
		this.deleteAccountUseCase = deleteAccountUseCase;
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void deleteAccount() {
		
	}
}
