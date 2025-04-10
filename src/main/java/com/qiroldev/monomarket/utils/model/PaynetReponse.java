package com.qiroldev.monomarket.utils.model;

import lombok.Data;

@Data
public class PaynetReponse<T> {
  private Long status;
  private String message;
  private Long timestamp = System.currentTimeMillis();
  private T data;
}
