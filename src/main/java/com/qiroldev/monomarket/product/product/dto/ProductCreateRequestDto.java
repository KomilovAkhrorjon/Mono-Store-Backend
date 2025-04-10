package com.qiroldev.monomarket.product.product.dto;

import com.qiroldev.monomarket.product.product.ProductStatus;
import com.qiroldev.monomarket.product.product.attribute.dto.AttributeDto;
import com.qiroldev.monomarket.product.product.description.dto.ProductDescriptionDto;
import com.qiroldev.monomarket.product.product.media.dto.ProductMediaCreateDto;
import com.qiroldev.monomarket.product.product.title.dto.ProductTitleDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Data;

@Data
public class ProductCreateRequestDto {

  private Long id;

  private List<ProductDescriptionDto> descriptions;

  @NotNull
  private Long warehouseId;

  @NotBlank
  private String model;

  @NotNull
  private Long categoryId;

  private Long countryId;

  private Long brandId;

  private List<AttributeDto> attributes;

  private List<ProductTitleDto> titles;

  private ProductStatus status;

  private List<Long> specifications;

  private List<Long> features;

  private List<ProductMediaCreateDto> mediaList;

}
