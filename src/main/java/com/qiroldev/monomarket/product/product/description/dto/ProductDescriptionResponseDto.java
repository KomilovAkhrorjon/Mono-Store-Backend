package com.qiroldev.monomarket.product.product.description.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDescriptionResponseDto {

  private Long id;

  private String description;

  private String shortDescription;

}
