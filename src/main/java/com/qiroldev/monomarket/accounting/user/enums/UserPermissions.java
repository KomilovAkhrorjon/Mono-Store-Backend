package com.qiroldev.monomarket.accounting.user.enums;

import lombok.Getter;

@Getter
public enum UserPermissions {
  PRODUCT_READ("product:read"),
  PRODUCT_WRITE("product:write"),
  SYSTEM_READ("system:read"),
  SYSTEM_WRITE("system:write"),
  ORDER_READ("order:read"),
  ORDER_WRITE("order:write"),
  SMS_REQUEST("sms:request"),
  SELLER_CREATE("seller:create"),
  SELLER_EDIT("seller:edit");

  private final String permission;

  UserPermissions(String permission) {
    this.permission = permission;
  }

}
