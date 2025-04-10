package com.qiroldev.monomarket.accounting.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserUpdateInfoDto {

  private String firstName;

  private String lastName;

}
