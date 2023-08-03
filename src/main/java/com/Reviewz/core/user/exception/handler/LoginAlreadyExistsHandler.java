package com.Reviewz.core.user.exception.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.Reviewz.core.user.exception.LoginAlreadyExistsException;
import com.Reviewz.core.user.genericException.ErrorResponse;

@ControllerAdvice
public class LoginAlreadyExistsHandler {

	@ExceptionHandler(LoginAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleExistingEmail(LoginAlreadyExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        
        setError(errorResponse, ex);
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
	
	private ErrorResponse setError(ErrorResponse errorResponse, LoginAlreadyExistsException ex) {
		errorResponse.setTimestamp(LocalDateTime.now().toString());
        errorResponse.setStatus(HttpStatus.CONFLICT.value());
        errorResponse.setError(HttpStatus.CONFLICT.getReasonPhrase());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setPath("/user");
        
        return errorResponse;
	}
	
}
