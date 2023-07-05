package com.Reviewz.entity.user.exception.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.Reviewz.entity.exception.ErrorResponse;
import com.Reviewz.entity.exception.ValidationError;

@ControllerAdvice
public class CreateUserExceptionHandler {

	@ExceptionHandler(ValidationError.class)
	public ResponseEntity<ErrorResponse> handleValidationError(ValidationError ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        
        setError(errorResponse, ex);
        
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
    }
	
	private ErrorResponse setError(ErrorResponse errorResponse, ValidationError ex) {
		errorResponse.setTimestamp(LocalDateTime.now().toString());
        errorResponse.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        errorResponse.setError(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setPath("/user");
        
        return errorResponse;
	}
}
