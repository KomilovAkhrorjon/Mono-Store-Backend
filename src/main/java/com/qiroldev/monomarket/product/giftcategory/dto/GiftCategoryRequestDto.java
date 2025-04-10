package com.qiroldev.monomarket.product.giftcategory.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GiftCategoryRequestDto {

  private Long id;

  private Boolean isActive;

  @NotNull
  private Long giftTypeId;

  @NotBlank
  private String link;

  @NotNull
  private Integer orderNo;

  private List<GiftCategoryTitleDto> titles;

}
