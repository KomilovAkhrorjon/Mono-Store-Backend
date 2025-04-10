package com.qiroldev.monomarket.accounting.address;

import com.qiroldev.monomarket.accounting.address.dto.AddressCreateRequestDto;
import java.security.Principal;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v0/address")
public interface AddressApi {

  @PostMapping()
  ResponseEntity<AddressEntity> create(AddressCreateRequestDto request, Principal principal);

  @GetMapping()
  ResponseEntity<List<AddressEntity>> findAll(Principal principal);
}
