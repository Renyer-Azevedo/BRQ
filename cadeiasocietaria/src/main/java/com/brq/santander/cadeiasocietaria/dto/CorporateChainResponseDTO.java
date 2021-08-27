package com.brq.santander.cadeiasocietaria.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CorporateChainResponseDTO {
  @JsonProperty("tipo_relac")
  private String tipoRelacionamento;
  @JsonProperty("tipo_docto")
  private String tipoDocumento;
  @JsonProperty("num_doc")
  private String numeroDocumento;
  @JsonProperty("nome_razao_social")
  private String razaoSocial;
  @JsonProperty("representante")
  private String representante;
  @JsonProperty("beneficiario")
  private String beneficiario;
  @JsonProperty("cargo_empresa")
  private String cargoEmpresa;
  @JsonProperty("cargo_conselho")
  private String cargoConselho;
  @JsonProperty("tipo_partic")
  private String tipoParticipacao;
  @JsonProperty("nivel")
  private Integer nivel;
  @JsonProperty("per_partic")
  private Long percentualParticipacao;
  @JsonProperty("origem")
  private String origem;
}
