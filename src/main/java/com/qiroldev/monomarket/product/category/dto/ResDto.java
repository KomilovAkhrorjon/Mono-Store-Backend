package com.qiroldev.monomarket.product.category.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResDto <T>{

  private Long id;

  private T data;

}
