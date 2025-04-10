package com.qiroldev.monomarket.product.country.dto;

import com.qiroldev.monomarket.common.dto.FullTitleResponseDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CountryFullResponseDto {

  private Long id;

  private String code;

  private List<FullTitleResponseDto> titles;
}
