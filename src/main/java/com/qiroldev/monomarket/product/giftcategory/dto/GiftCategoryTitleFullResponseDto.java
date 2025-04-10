package com.qiroldev.monomarket.product.giftcategory.dto;

import com.qiroldev.monomarket.product.system.enums.Lang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class GiftCategoryTitleFullResponseDto {

  private Long id;

  private String title;

  private String photo;

  private String banner;

  private Lang lang;
}
