package com.qiroldev.monomarket.product.category.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParentCategoryDto {

  private Long id;

  private String title;

  private String icon;

  private Integer priority;

  private ParentCategoryDto parent;
}
