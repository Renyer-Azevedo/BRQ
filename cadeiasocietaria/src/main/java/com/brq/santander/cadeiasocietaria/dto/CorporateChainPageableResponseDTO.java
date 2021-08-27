package com.brq.santander.cadeiasocietaria.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CorporateChainPageableResponseDTO{
      @JsonProperty("_pageable")
      private PageableTemplateDTO pageable;
      @JsonProperty("_content")
      private List<Object> content;
}
