package com.qiroldev.monomarket.accounting.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerLoginRequest {

  private String phone;
  private String smsCode;
}
