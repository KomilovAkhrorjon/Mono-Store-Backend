package com.qiroldev.monomarket.accounting.address;

import com.qiroldev.monomarket.accounting.address.dto.AddressCreateRequestDto;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AddressController implements AddressApi {

  private final AddressService addressService;

  @Override
  public ResponseEntity<AddressEntity> create(AddressCreateRequestDto request, Principal principal) {
    return ResponseEntity.ok(addressService.create(request, principal));
  }

  @Override
  public ResponseEntity<List<AddressEntity>> findAll(Principal principal) {
    return ResponseEntity.ok(addressService.findAll(principal));
  }
}
