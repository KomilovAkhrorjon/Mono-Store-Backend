package com.qiroldev.monomarket.accounting.address;

import com.qiroldev.monomarket.accounting.address.dto.AddressCreateRequestDto;
import java.security.Principal;
import java.util.List;

public interface AddressService {

  AddressEntity create(AddressCreateRequestDto request, Principal principal);

  AddressEntity findById(Long addressId);

  List<AddressEntity> findAll(Principal principal);
}
