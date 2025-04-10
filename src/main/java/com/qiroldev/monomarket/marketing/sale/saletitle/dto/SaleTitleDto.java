package com.qiroldev.monomarket.marketing.sale.saletitle.dto;

import com.qiroldev.monomarket.marketing.sale.media.dto.SaleMediaDto;
import com.qiroldev.monomarket.product.system.enums.Lang;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Data;

@Data
public class SaleTitleDto {

  @NotBlank
  private String title;

  @NotBlank
  private String description;

  @NotNull
  private Lang lang;

  private List<SaleMediaDto> medias;

}
