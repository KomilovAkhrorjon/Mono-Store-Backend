package com.qiroldev.monomarket.product.product.dto;

import com.qiroldev.monomarket.common.media.MediaResponseDto;
import com.qiroldev.monomarket.product.brand.dto.BrandResponseDto;
import com.qiroldev.monomarket.product.category.dto.ParentCategoryDto;
import com.qiroldev.monomarket.product.product.description.dto.ProductDescriptionResponseDto;
import com.qiroldev.monomarket.product.product.sku.dto.SkuResponseDto;
import com.qiroldev.monomarket.product.productfeature.dto.ProductFeatureResponseDto;
import com.qiroldev.monomarket.product.productspecification.dto.ProductSpecificationResponseDto;
import com.qiroldev.monomarket.product.reviews.dto.ProductRatingResponseDto;
import com.qiroldev.monomarket.product.system.enums.Currency;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailResponseDto {

  private Long id;

  private BrandResponseDto brand;

  private ParentCategoryDto category;

  private Currency currency;

  private String title;

  private Boolean isLiked;

  private List<String> attributes;

  private List<MediaResponseDto> media;

  private ProductDescriptionResponseDto description;

  private List<ProductSpecificationResponseDto> specifications;

  private ProductRatingResponseDto rating;

  private List<ProductFeatureResponseDto> features;

  private List<SkuResponseDto> skuList;

}
