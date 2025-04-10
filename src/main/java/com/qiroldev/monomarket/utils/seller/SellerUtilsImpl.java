package com.qiroldev.monomarket.utils.seller;

import com.qiroldev.monomarket.accounting.seller.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerUtilsImpl implements SellerUtils {

  private final SellerService sellerService;

  @Override
  public Boolean sellerCheck(Long id) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentUsername = authentication.getName();
    System.out.println("currentUsername = " + currentUsername);
    return sellerService.getUserByUsername(currentUsername).getId().equals(id);
  }

}
