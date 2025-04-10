package com.qiroldev.monomarket.product.productspecification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductSpecificationResponseDto {

  private Long id;

  private String title;

  private String description;

  private Long categoryId;

  private String categoryTitle;

  private String categoryDescription;

}
