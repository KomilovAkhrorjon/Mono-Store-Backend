package com.qiroldev.monomarket.product.giftcategory.dto;

import com.qiroldev.monomarket.product.gifttype.dto.GiftTypeResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GiftCategoryResponseDto {

  private Long id;

  private String title;

  private String photo;

  private String banner;

  private GiftTypeResponseDto giftType;

  private String link;

  private Integer orderNo;

}
