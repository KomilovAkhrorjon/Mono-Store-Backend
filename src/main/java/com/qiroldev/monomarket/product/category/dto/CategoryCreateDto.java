package com.qiroldev.monomarket.product.category.dto;

import com.qiroldev.monomarket.product.category.title.dto.CategoryTitleRequestDto;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Getter;

@Getter
public class CategoryCreateDto {

  Long id;

  Long parentId;

  @NotNull
  Boolean isPublic;

  @NotNull
  String icon;

  Integer priority;

  List<CategoryTitleRequestDto> titles;

}
