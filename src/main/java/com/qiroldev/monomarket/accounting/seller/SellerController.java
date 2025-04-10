package com.qiroldev.monomarket.accounting.seller;

import com.qiroldev.monomarket.accounting.seller.dto.SellerCreateDto;
import com.qiroldev.monomarket.accounting.seller.dto.SellerUpdateDto;
import jakarta.validation.Valid;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v0/seller")
@RequiredArgsConstructor
public class SellerController {

  private final SellerService service;

  @PostMapping()
  @PreAuthorize("hasAnyAuthority('seller:create')")
  public ResponseEntity<?> create(@RequestBody SellerCreateDto request) {
    return service.create(request);
  }

  @PutMapping()
  @PreAuthorize("hasAnyAuthority('seller:edit')")
  public ResponseEntity<?> update(@RequestBody @Valid SellerUpdateDto request,
      Principal principal) {
    return service.update(request, principal);
  }

}
