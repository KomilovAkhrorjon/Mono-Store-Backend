package com.qiroldev.monomarket.product.specification.dto;

import com.qiroldev.monomarket.product.specifficationcategory.dto.SpecificationCategoryResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SpecificationResponseDto {

  private Long id;

  private String title;

  private String description;

  private SpecificationCategoryResponseDto category;

}
