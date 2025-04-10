package com.qiroldev.monomarket.product.product.sku.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Data;

@Data
public class SkuCreateRequestDto {

  private Long id;

  @NotNull
  private Long warehouseId;

  @NotNull
  private List<Long> specifications;

}
