package com.qiroldev.monomarket.product.category.dto;

import com.qiroldev.monomarket.common.dto.FullTitleResponseDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryFullResponseDto {

  private Long id;

  private List<FullTitleResponseDto> titles;

  private Boolean isPublic;

  private String icon;

  private Integer priority;

  private List<CategoryFullResponseDto> children;

}
