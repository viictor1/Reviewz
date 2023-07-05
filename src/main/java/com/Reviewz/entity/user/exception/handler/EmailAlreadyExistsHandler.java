package com.Reviewz.entity.user.exception.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.Reviewz.entity.exception.ErrorResponse;
import com.Reviewz.entity.user.exception.EmailAlreadyExistsException;

@ControllerAdvice
public class EmailAlreadyExistsHandler {

	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleExistingEmail(EmailAlreadyExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        
        setError(errorResponse, ex);
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
	
	private ErrorResponse setError(ErrorResponse errorResponse, EmailAlreadyExistsException ex) {
		errorResponse.setTimestamp(LocalDateTime.now().toString());
        errorResponse.setStatus(HttpStatus.CONFLICT.value());
        errorResponse.setError(HttpStatus.CONFLICT.getReasonPhrase());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setPath("/user");
        
        return errorResponse;
	}
	
}
