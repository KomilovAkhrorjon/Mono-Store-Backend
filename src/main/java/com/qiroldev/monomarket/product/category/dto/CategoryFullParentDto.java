package com.qiroldev.monomarket.product.category.dto;

import com.qiroldev.monomarket.common.dto.FullTitleResponseDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CategoryFullParentDto {

  private Long id;

  private List<FullTitleResponseDto> titles;

  private Boolean isPublic;

  private String icon;

  private Integer priority;

  private CategoryFullParentDto parent;

}
