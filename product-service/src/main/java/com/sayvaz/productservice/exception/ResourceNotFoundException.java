package com.sayvaz.productservice.exception;

public class ResourceNotFoundException extends RuntimeException{
	

	public ResourceNotFoundException(String message) {
		super(message);
	}

	private static final long serialVersionUID = -4947451521006772811L;
}
