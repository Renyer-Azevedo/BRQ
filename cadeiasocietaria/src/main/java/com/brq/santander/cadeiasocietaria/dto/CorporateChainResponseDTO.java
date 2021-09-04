package com.brq.santander.cadeiasocietaria.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CorporateChainResponseDTO {
	
  private String relationshipType;
  private String documentType;
  private String documentNumber;
  private String companyName;
  private Boolean isRepresentative;
  private Boolean isBeneficiary;
  private String jobCompany;
  private String positionAdvice;
  private String participationType;
  private Integer level;
  private Integer percentageParticipation;
  private String origin;
  
}
