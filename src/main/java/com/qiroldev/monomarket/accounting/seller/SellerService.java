package com.qiroldev.monomarket.accounting.seller;

import com.qiroldev.monomarket.accounting.seller.dto.SellerCreateDto;
import com.qiroldev.monomarket.accounting.seller.dto.SellerLoginRequest;
import com.qiroldev.monomarket.accounting.seller.dto.SellerUpdateDto;
import java.security.Principal;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;

public interface SellerService {

  ResponseEntity<?> create(SellerCreateDto request);

  ResponseEntity<?> login(SellerLoginRequest request) throws BadRequestException;

  ResponseEntity<?> update(SellerUpdateDto request, Principal principal);

  SellerModel getUserByUsername(String username);
}
