package com.qiroldev.monomarket.product.productspecification.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductSpecificationCreateRequestDto {

  @NotNull
  private Long productId;

  @NotNull
  private Long specificationId;

}
