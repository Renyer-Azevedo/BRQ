package com.brq.santander.cadeiasocietaria.model;

import java.math.BigDecimal;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(value = "cadeia_societaria_online")
public class CoporateChainOnline {

	@PrimaryKeyColumn(ordinal = 0,type = PrimaryKeyType.PARTITIONED)
	private String penumper;
	private String faixas;
	private String flag_1_nivel_cadeia;
	private String flag_bf;
	private String indicador_completude;
	private BigDecimal perc_completude;
	
}
