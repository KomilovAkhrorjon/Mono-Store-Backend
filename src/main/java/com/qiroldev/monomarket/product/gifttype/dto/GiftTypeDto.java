package com.qiroldev.monomarket.product.gifttype.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GiftTypeDto {

  private Long id;

  @NotBlank
  private String type;

  private Boolean isActive;

  private List<GiftTypeTitleDto> titles;
}
