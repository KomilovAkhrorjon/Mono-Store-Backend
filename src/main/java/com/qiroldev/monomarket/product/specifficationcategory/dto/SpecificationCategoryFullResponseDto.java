package com.qiroldev.monomarket.product.specifficationcategory.dto;

import com.qiroldev.monomarket.common.dto.FullTitleResponseDto;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SpecificationCategoryFullResponseDto {

  private Long id;

  private Boolean isActive;

  private LocalDateTime updatedAt;

  private List<FullTitleResponseDto> titles;

}
