package com.qiroldev.monomarket.external.warehouse.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ProductOptionDto {

  private Long id;

  private String title;

  private String sku;

  private String code;

  private Double cost;

  private Double sellingPrice;

  private Long barcode;

  private Integer availableAmount;

  private Long marketSellingPrice;

  private Long marketPrice;
}
