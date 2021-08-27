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
public class PageableTemplateDTO {
  @JsonProperty("_limit")
  private Integer limit;
  @JsonProperty("_offset")
  private Integer offset;
  @JsonProperty("_pageNumber")
  private Integer pageNumber;
  @JsonProperty("_pageElements")
  private Integer pageElements;
  @JsonProperty("_totalPages")
  private Integer totalPages;
  @JsonProperty("_totalElements")
  private Integer totalElements;
}
