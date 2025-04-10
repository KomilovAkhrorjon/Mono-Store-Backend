package com.qiroldev.monomarket.product.feature.dto;

import com.qiroldev.monomarket.common.dto.FullTitleResponseDto;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FeatureFullResponseDto {

  private Long id;

  private List<FullTitleResponseDto> titles;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  private LocalDateTime deletedAt;

}
