package com.Reviewz.core.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ValidationError extends Exception{

	private static final long serialVersionUID = 1L;

	public ValidationError(String msg) {
		super(msg);
	}
}
