package com.qiroldev.monomarket.external.warehouse.dto;

import com.qiroldev.monomarket.product.system.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductPriceResponseDto {

  private Long productId;

  private Long price;

  private Currency currency;

  private Long discountPrice;

}
