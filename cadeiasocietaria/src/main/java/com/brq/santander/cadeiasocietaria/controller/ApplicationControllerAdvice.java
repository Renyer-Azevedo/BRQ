package com.brq.santander.cadeiasocietaria.controller;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.brq.santander.cadeiasocietaria.dto.ErrorDTO;
import com.brq.santander.cadeiasocietaria.dto.ErrorsDTO;
import com.brq.santander.cadeiasocietaria.exception.BusinessRuleException;

import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class ApplicationControllerAdvice {
	
	@ExceptionHandler(BusinessRuleException.class)
	public ResponseEntity<ErrorDTO> businessRuleException (BusinessRuleException ex) {
		
		ErrorsDTO errors = null;
		ErrorDTO error = null;
		
		switch (ex.getStatusCode().value()) {
		case 400:
			errors = new ErrorsDTO(String.valueOf(ex.getStatusCode().value()), "", ex.getMessage());
			error = new ErrorDTO(String.valueOf(ex.getStatusCode().value()), ex.getStatusCode().getReasonPhrase(), ex.getMessage(), LocalDateTime.now().toString(), String.valueOf(Arrays.hashCode(ex.getStackTrace())), Arrays.asList(errors));
			break;
			
		case 404:
			errors = new ErrorsDTO(String.valueOf(ex.getStatusCode().value()), "penumpri", ex.getMessage());
			error = new ErrorDTO(String.valueOf(ex.getStatusCode().value()), ex.getStatusCode().getReasonPhrase(), ex.getMessage(), LocalDateTime.now().toString(), String.valueOf(Arrays.hashCode(ex.getStackTrace())), Arrays.asList(errors));
			break;

		default:
			break;
		}
		
		return new ResponseEntity<>(error, ex.getStatusCode());
		
	}
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorDTO exceptionInternalError(RuntimeException ex) {
		ErrorsDTO errors = new ErrorsDTO(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), "", ex.getMessage());
		return new ErrorDTO(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage(), LocalDateTime.now().toString(), String.valueOf(Arrays.hashCode(ex.getStackTrace())), Arrays.asList(errors));
	}

}
