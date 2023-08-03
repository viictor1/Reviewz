package com.Reviewz.core.user.genericException.handler;

import com.Reviewz.core.user.genericException.ErrorResponse;
import com.Reviewz.core.user.genericException.ValidationError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ValidationExceptionHandler {

	@ExceptionHandler(ValidationError.class)
	public ResponseEntity<ErrorResponse> handleValidationError(ValidationError ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        setError(errorResponse, ex, request);
        
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
    }
	
	private ErrorResponse setError(ErrorResponse errorResponse, ValidationError ex, HttpServletRequest request) {
		errorResponse.setTimestamp(LocalDateTime.now().toString());
        errorResponse.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        errorResponse.setError(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setPath(request.getRequestURI());
        
        return errorResponse;
	}
}
