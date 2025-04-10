package com.qiroldev.monomarket.product.country.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CountryResponseDto {

  private Long id;

  private String code;

  private String title;
}
