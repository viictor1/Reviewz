package com.Reviewz.entrypoint.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class LoginController {

	@PostMapping("/login")
	public Response login(@RequestBody @Valid Input input) {
		return new Response();
	}
	
	private record Input(String email, String password) {};
	private record Response() {};
}
