package com.brq.santander.cadeiasocietaria.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompletenessCorporateChainDTO {
	
	private String pnumper;
	private String completionIndicator;
	private String completenessPercentageFlag;

}
