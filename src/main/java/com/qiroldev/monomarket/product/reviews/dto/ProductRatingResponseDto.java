package com.qiroldev.monomarket.product.reviews.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductRatingResponseDto {

  private Long productId;

  private Double rating;

  private Long reviewCount;

}
