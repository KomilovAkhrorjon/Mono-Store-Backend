package com.qiroldev.monomarket.product.gifttype.dto;

import com.qiroldev.monomarket.common.dto.FullTitleResponseDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class GiftTypeFullResponseDto {

  private Long id;

  private List<FullTitleResponseDto> titles;

  private String type;

  private Boolean isActive;

}
