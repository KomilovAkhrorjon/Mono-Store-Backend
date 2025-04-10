package com.qiroldev.monomarket.product.product.sku.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkuResponseDto {

  private Long id;

  private String sku;

  private Long price;

  private Long discount;

  private Integer availableQuantity;

  private List<Long> specifications;

}
