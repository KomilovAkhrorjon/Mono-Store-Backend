package com.qiroldev.monomarket.product.brand.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandResponseDto {

  private Long id;

  private String title;

  private String logo;

  private String titleLink;

  private LocalDateTime createdAt;
}
