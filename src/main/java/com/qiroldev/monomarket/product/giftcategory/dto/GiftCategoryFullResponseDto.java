package com.qiroldev.monomarket.product.giftcategory.dto;

import com.qiroldev.monomarket.product.gifttype.dto.GiftTypeFullResponseDto;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class GiftCategoryFullResponseDto {

  private Long id;

  private Boolean isActive;

  private List<GiftCategoryTitleFullResponseDto> titles;

  private LocalDateTime deletedAt;

  private GiftTypeFullResponseDto giftType;

  private String link;

  private Integer orderNo;

}
