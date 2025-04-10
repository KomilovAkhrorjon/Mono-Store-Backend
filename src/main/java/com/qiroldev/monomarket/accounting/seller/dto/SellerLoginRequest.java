package com.qiroldev.monomarket.accounting.seller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SellerLoginRequest {

  @NotBlank
  String username;

  @NotBlank
  String password;

}
