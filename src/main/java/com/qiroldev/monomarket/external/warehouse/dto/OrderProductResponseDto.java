package com.qiroldev.monomarket.external.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderProductResponseDto {

  private Double price;

  private Integer amount;

  private ProductOptionDto productOption;

}
