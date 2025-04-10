package com.qiroldev.monomarket.marketing.sale.dto;

import com.qiroldev.monomarket.marketing.sale.saletitle.dto.SaleTitleDto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.util.List;
import lombok.Data;

@Data
public class SaveSaleRequestDto {

  @Max(3)
  @Min(3)
  private List<SaleTitleDto> titles;

  private String color;
}
