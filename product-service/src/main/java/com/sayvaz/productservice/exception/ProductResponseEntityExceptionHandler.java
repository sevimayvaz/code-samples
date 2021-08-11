package com.sayvaz.productservice.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.log4j.Log4j2;

@ControllerAdvice
@Log4j2
public class ProductResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ProductErrorResponse> handle(ResourceNotFoundException ex, WebRequest request) {
		ProductErrorResponse error = new ProductErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(),
				LocalDateTime.now());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler(ProductGeneralException.class)
	public ResponseEntity<ProductErrorResponse> handle(ProductGeneralException ex, WebRequest request) {
		ProductErrorResponse error = new ProductErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(),
				LocalDateTime.now());

		log.error("Unhandled exception occured : ", ex);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR); 
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ProductErrorResponse> handle(Exception ex, WebRequest request) {
		ProductErrorResponse error = new ProductErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(),
				LocalDateTime.now());

		log.error("Unhandled exception occured : ", ex);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR); 
	}

}
