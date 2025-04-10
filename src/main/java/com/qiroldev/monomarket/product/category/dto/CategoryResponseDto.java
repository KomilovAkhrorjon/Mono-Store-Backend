package com.qiroldev.monomarket.product.category.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CategoryResponseDto {

  private Long id;

  private String title;

  private String icon;

  private Integer priority;

  private ParentCategoryDto parent;

  private List<ChildCategoryDto> children;

}
