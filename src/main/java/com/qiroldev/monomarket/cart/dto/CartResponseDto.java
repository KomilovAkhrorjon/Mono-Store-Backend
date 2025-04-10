package com.qiroldev.monomarket.cart.dto;

import com.qiroldev.monomarket.common.media.MediaResponseDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartResponseDto {

  private Long id;

  private String productName;

  private List<MediaResponseDto> media;

  private CartSkuResponseDto sku;

}
