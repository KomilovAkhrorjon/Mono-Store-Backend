package com.qiroldev.monomarket.product.category.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChildCategoryDto {

  private Long id;

  private String title;

  private String icon;

  private Integer priority;

  private List<ChildCategoryDto> children;
}
