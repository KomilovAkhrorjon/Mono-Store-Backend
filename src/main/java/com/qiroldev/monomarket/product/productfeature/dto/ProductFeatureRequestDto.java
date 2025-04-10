package com.qiroldev.monomarket.product.productfeature.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Data;

@Data
public class ProductFeatureRequestDto {

  @NotNull
  private Long productId;

  @NotNull
  private List<Long> features;

}
