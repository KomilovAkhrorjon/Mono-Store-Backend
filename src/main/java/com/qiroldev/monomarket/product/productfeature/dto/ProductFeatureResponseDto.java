package com.qiroldev.monomarket.product.productfeature.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductFeatureResponseDto {

  private Long id;

  private String title;

  private String description;

}
