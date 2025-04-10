package com.qiroldev.monomarket.marketing.sale.media.dto;

import com.qiroldev.monomarket.product.product.media.enums.MediaSize;
import com.qiroldev.monomarket.product.product.media.enums.MediaType;
import lombok.Data;

@Data
public class SaleMediaDto {

  private String mediaKey;

  private String url;

  private MediaType type;

  private String name;

  private String savedName;

  private MediaSize size;

}
