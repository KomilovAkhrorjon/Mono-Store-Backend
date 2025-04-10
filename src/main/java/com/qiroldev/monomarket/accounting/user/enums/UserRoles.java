package com.qiroldev.monomarket.accounting.user.enums;

import static com.qiroldev.monomarket.accounting.user.enums.UserPermissions.ORDER_READ;
import static com.qiroldev.monomarket.accounting.user.enums.UserPermissions.ORDER_WRITE;
import static com.qiroldev.monomarket.accounting.user.enums.UserPermissions.PRODUCT_READ;
import static com.qiroldev.monomarket.accounting.user.enums.UserPermissions.PRODUCT_WRITE;
import static com.qiroldev.monomarket.accounting.user.enums.UserPermissions.SELLER_CREATE;
import static com.qiroldev.monomarket.accounting.user.enums.UserPermissions.SELLER_EDIT;
import static com.qiroldev.monomarket.accounting.user.enums.UserPermissions.SMS_REQUEST;
import static com.qiroldev.monomarket.accounting.user.enums.UserPermissions.SYSTEM_READ;
import static com.qiroldev.monomarket.accounting.user.enums.UserPermissions.SYSTEM_WRITE;

import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
public enum   UserRoles {
  ADMIN(Set.of(PRODUCT_READ, PRODUCT_WRITE, SYSTEM_READ, SYSTEM_WRITE,
      ORDER_WRITE, ORDER_READ, SELLER_CREATE, SELLER_EDIT, SMS_REQUEST)),
  USER(Set.of(PRODUCT_READ, SYSTEM_READ)),
  SELLER(Set.of(PRODUCT_READ, ORDER_READ, ORDER_WRITE, SYSTEM_READ, SELLER_EDIT, PRODUCT_WRITE)),
  MARKET(Set.of(PRODUCT_READ, ORDER_READ, ORDER_WRITE, SYSTEM_READ)),
  EMPLOYEE(Set.of(PRODUCT_READ, ORDER_READ, ORDER_WRITE, SYSTEM_READ, PRODUCT_WRITE)),
  MANAGER(Set.of(PRODUCT_READ, ORDER_READ, ORDER_WRITE, SYSTEM_READ, PRODUCT_WRITE)),
  GUEST(Set.of(PRODUCT_READ, SMS_REQUEST));

  private final Set<UserPermissions> permissions;

  UserRoles(Set<UserPermissions> permissions) {
    this.permissions = permissions;
  }

  public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
    Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
        .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
        .collect(Collectors.toSet());
    permissions.add(new SimpleGrantedAuthority(this.name()));
    return permissions;
  }
}
