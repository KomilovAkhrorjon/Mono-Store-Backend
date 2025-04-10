package com.qiroldev.monomarket.product.product.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qiroldev.monomarket.common.media.MediaResponseDto;
import com.qiroldev.monomarket.product.brand.dto.BrandResponseDto;
import com.qiroldev.monomarket.product.system.enums.Currency;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductListResponseDto {

  private Long id;

  private String title;

  private BrandResponseDto brand;

  private Long categoryId;

  private List<MediaResponseDto> medias;

private Long actualPrice;

  private Long discountPrice;

  private Currency currency;

  private Double rating;

  @JsonIgnore
  private Long warehouseId;

}
