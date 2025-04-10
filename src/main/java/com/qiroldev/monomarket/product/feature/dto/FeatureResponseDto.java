package com.qiroldev.monomarket.product.feature.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FeatureResponseDto {

  private Long id;

  private String title;

  private String description;

  private LocalDateTime createdAt;

}
