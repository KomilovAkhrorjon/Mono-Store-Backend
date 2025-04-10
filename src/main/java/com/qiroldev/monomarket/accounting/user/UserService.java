package com.qiroldev.monomarket.accounting.user;

import com.qiroldev.monomarket.accounting.user.dto.CreateUserDto;
import com.qiroldev.monomarket.accounting.user.dto.CustomerLoginRequest;
import com.qiroldev.monomarket.accounting.user.dto.CustomerPhoneRequest;
import com.qiroldev.monomarket.accounting.user.dto.MinimumUserResponseDto;
import com.qiroldev.monomarket.accounting.user.dto.UserUpdateInfoDto;
import jakarta.validation.Valid;
import java.security.Principal;
import org.springframework.http.ResponseEntity;

public interface UserService {

  ResponseEntity<?> phone(CustomerPhoneRequest request);

  ResponseEntity<?> login(CustomerLoginRequest request);

  UserEntity createSellerUser(CreateUserDto request);

  String loginWithUsername(String username);

  UserEntity getUserByUsername(String username);

  UserEntity getUser(Principal principal);

  void updateUserInfo(@Valid UserUpdateInfoDto request, Principal principal);

  MinimumUserResponseDto getUserInfo(Principal principal);
}
