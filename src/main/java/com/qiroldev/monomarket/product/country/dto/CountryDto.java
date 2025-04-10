package com.qiroldev.monomarket.product.country.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CountryDto {

  private Long id;

  @NotBlank
  private String code;

  private List<CountryTitlesDto> titles;
}
