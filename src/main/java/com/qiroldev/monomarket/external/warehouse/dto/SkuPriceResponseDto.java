package com.qiroldev.monomarket.external.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkuPriceResponseDto {

  private Long skuId;

  private Long price;

  private Long discountPrice;

  private Integer availableQuantity;

  private String sku;

}
