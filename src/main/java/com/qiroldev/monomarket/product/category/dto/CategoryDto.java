package com.qiroldev.monomarket.product.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryDto {

  private Long id;
  private String title;
  private String icon;
  private Long parentId;

}
