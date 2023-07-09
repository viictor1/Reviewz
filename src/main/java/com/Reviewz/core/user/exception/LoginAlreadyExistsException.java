package com.Reviewz.core.user.exception;

public class LoginAlreadyExistsException extends Exception{

	private static final long serialVersionUID = 1L;

	public LoginAlreadyExistsException(String msg) {
		super(msg);
	}
}
