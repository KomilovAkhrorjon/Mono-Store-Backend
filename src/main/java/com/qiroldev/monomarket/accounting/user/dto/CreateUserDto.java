package com.qiroldev.monomarket.accounting.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateUserDto {

  String username;

  String password;
}
