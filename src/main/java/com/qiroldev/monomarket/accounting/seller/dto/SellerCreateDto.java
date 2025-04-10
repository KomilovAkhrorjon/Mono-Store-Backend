package com.qiroldev.monomarket.accounting.seller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellerCreateDto {

  @NotBlank
  String title;

  @NotBlank
  String username;

  @NotBlank
  String password;

}
