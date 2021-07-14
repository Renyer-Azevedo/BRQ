package com.brq.santander.cadeiasocietaria.exception;

import org.springframework.http.HttpStatus;

public class BusinessRuleException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private HttpStatus statusCode;

	public BusinessRuleException(String message, Throwable cause, HttpStatus statusCode) {
		super(message, cause);
		this.statusCode = statusCode;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}
	
	

}
