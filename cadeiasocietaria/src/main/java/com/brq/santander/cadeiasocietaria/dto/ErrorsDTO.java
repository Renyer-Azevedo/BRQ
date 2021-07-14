package com.brq.santander.cadeiasocietaria.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorsDTO {
	
	private String _code;
	private String _field;
	private String _message;

}
