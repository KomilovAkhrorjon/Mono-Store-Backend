package com.qiroldev.monomarket.product.specification.dto;

import com.qiroldev.monomarket.common.dto.FullTitleResponseDto;
import com.qiroldev.monomarket.product.specifficationcategory.dto.SpecificationCategoryFullResponseDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class SpecificationFullResponseDto {

  private Long id;

  private List<FullTitleResponseDto> titles;

  private SpecificationCategoryFullResponseDto category;

}
