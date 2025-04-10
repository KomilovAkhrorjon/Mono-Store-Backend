package com.qiroldev.monomarket.cart.dto;

import com.qiroldev.monomarket.external.warehouse.dto.SkuPriceResponseDto;
import com.qiroldev.monomarket.product.productspecification.dto.ProductSpecificationResponseDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartSkuResponseDto {

  private Long id;

  private String sku;

  private SkuPriceResponseDto price;

  private Integer quantity;

  private List<ProductSpecificationResponseDto> specifications;

}
