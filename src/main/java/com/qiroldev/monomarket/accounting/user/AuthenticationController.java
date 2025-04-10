package com.qiroldev.monomarket.accounting.user;

import com.qiroldev.monomarket.accounting.seller.SellerService;
import com.qiroldev.monomarket.accounting.seller.dto.SellerLoginRequest;
import com.qiroldev.monomarket.accounting.user.dto.CustomerLoginRequest;
import com.qiroldev.monomarket.accounting.user.dto.CustomerPhoneRequest;
import com.qiroldev.monomarket.accounting.user.dto.MinimumUserResponseDto;
import com.qiroldev.monomarket.accounting.user.dto.UserUpdateInfoDto;
import jakarta.validation.Valid;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v0/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final UserService service;
  private final SellerService sellerService;

  @PostMapping("/phone")
  public ResponseEntity<?> register(@RequestBody @Valid CustomerPhoneRequest request) {
    return service.phone(request);
  }

  @PostMapping()
  public ResponseEntity<?> login(@RequestBody CustomerLoginRequest request) {
    return service.login(request);
  }

  @PutMapping()
  public ResponseEntity<Void> updateUserInfo(@RequestBody @Valid UserUpdateInfoDto request,
      Principal principal) {
    service.updateUserInfo(request, principal);

    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  @GetMapping("/me")
  public ResponseEntity<MinimumUserResponseDto> getMe(Principal principal) {
    return ResponseEntity.ok(service.getUserInfo(principal));
  }

  @PostMapping("/seller/login")
  public ResponseEntity<?> login(@RequestBody @Valid SellerLoginRequest request)
      throws BadRequestException {
    return sellerService.login(request);
  }

}