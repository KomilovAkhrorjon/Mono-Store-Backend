package com.qiroldev.monomarket.product.product.dto;

import com.qiroldev.monomarket.common.media.MediaResponseDto;
import com.qiroldev.monomarket.product.brand.dto.BrandDto;
import com.qiroldev.monomarket.product.category.dto.CategoryFullParentDto;
import com.qiroldev.monomarket.product.country.dto.CountryDto;
import com.qiroldev.monomarket.product.feature.dto.FeatureFullResponseDto;
import com.qiroldev.monomarket.product.product.ProductStatus;
import com.qiroldev.monomarket.product.product.attribute.dto.AttributeDto;
import com.qiroldev.monomarket.product.product.description.dto.ProductDescriptionDto;
import com.qiroldev.monomarket.product.product.sku.dto.SkuFullResponseDto;
import com.qiroldev.monomarket.product.product.title.dto.ProductTitleDto;
import com.qiroldev.monomarket.product.specification.dto.SpecificationFullResponseDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductFullDetailResponseDto {

  private Long id;

  private List<ProductDescriptionDto> descriptions;

  private Long warehouseId;

  private ProductStatus status;

  private String model;

  private List<ProductTitleDto> titles;

  private CategoryFullParentDto category;

  private CountryDto country;

  private BrandDto brand;

  private List<AttributeDto> attributes;

  private List<SpecificationFullResponseDto> specifications;

  private List<FeatureFullResponseDto> features;

  private List<MediaResponseDto> mediaList;

  private List<SkuFullResponseDto> skuList;

}
