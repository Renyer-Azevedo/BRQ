package com.brq.santander.cadeiasocietaria.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaDTO {
	
	private String penumper;
	private String indicador_completude;
	private String faixas;
	private Double perc_completude;
	private String flag_1_nivel_cadeia;
	private String flag_bf;

}
