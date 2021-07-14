package com.brq.santander.cadeiasocietaria.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {
	
	private String _errorCode;
	private String _message;
	private String _details;
	private String _timestamp;
	private String _traceId;
	List<ErrorsDTO> errors;

}
