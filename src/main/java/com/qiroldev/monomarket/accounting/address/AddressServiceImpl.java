package com.qiroldev.monomarket.accounting.address;

import com.qiroldev.monomarket.accounting.address.dto.AddressCreateRequestDto;
import com.qiroldev.monomarket.accounting.user.UserService;
import com.qiroldev.monomarket.utils.Utils;
import com.qiroldev.monomarket.utils.exception.ResourceNotFoundException;
import com.qiroldev.monomarket.utils.message.Message;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

  private final AddressRepository addressRepository;
  private final UserService userService;
  private final Utils utils;

  @Override
  public AddressEntity create(AddressCreateRequestDto request, Principal principal) {
    return addressRepository.save(new AddressEntity(
        request.getName(),
        request.getNote(),
        request.getLat(),
        request.getLon(),
        userService.getUser(principal)
    ));
  }

  @Override
  public AddressEntity findById(Long addressId) {
    return addressRepository.findById(addressId)
        .orElseThrow(() ->
            new ResourceNotFoundException(addressId.toString(), List.of(
                utils.getMessage(Message.ADDRESS_NOT_FOUND)
            ))
        );
  }

  @Override
  public List<AddressEntity> findAll(Principal principal) {
    return addressRepository.findAllByUserId(userService.getUser(principal).getId());
  }
}
