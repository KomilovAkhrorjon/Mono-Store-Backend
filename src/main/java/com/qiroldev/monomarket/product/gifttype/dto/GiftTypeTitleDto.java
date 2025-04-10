package com.qiroldev.monomarket.product.gifttype.dto;

import com.qiroldev.monomarket.product.system.enums.Lang;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GiftTypeTitleDto {

  private Long id;

  @NotBlank
  private String title;

  @NotBlank
  private String subtitle;

  private Lang lang;

}
