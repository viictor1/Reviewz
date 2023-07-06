package com.Reviewz.core.user.exception;

public class EmailAlreadyExistsException extends Exception{

	private static final long serialVersionUID = 1L;

	public EmailAlreadyExistsException(String msg) {
		super(msg);
	}
}
