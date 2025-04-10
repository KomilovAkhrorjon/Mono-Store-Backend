package com.qiroldev.monomarket.product.product.dto;

import com.qiroldev.monomarket.product.giftcategory.dto.GiftCategoryResponseDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductListResponseWithGiftCategoryDto {

  private GiftCategoryResponseDto giftCategory;

  private List<ProductListResponseDto> products;

}
