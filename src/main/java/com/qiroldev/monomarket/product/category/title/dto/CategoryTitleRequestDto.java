package com.qiroldev.monomarket.product.category.title.dto;

import com.qiroldev.monomarket.product.category.CategoryEntity;
import com.qiroldev.monomarket.product.system.enums.Lang;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryTitleRequestDto {

  private Long id;

  @NotNull
  private String title;

  @NotNull
  private Lang lang;

  private CategoryEntity category;
}
