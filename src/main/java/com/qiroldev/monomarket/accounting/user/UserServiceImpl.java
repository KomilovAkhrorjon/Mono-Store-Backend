package com.qiroldev.monomarket.accounting.user;

import com.qiroldev.monomarket.accounting.user.dto.CreateUserDto;
import com.qiroldev.monomarket.accounting.user.dto.CustomerLoginRequest;
import com.qiroldev.monomarket.accounting.user.dto.CustomerPhoneRequest;
import com.qiroldev.monomarket.accounting.user.dto.MinimumUserResponseDto;
import com.qiroldev.monomarket.accounting.user.dto.UserUpdateInfoDto;
import com.qiroldev.monomarket.accounting.user.enums.UserRoles;
import com.qiroldev.monomarket.accounting.user.tempcustomer.TempCustomerModel;
import com.qiroldev.monomarket.accounting.user.tempcustomer.TempCustomerRepository;
import com.qiroldev.monomarket.external.eskiz.EskizSmsComponent;
import com.qiroldev.monomarket.external.eskiz.dto.SmsRequest;
import com.qiroldev.monomarket.jwt.JwtUtil;
import com.qiroldev.monomarket.utils.Utils;
import com.qiroldev.monomarket.utils.exception.BadRequestException;
import com.qiroldev.monomarket.utils.message.Message;
import java.security.Principal;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final CustomerRepository repository;
  private final JwtUtil jwtUtil;
  private final TempCustomerRepository tempRepository;
  private final Utils utils;
  private final EskizSmsComponent eskizSmsComponent;

  @Override
  public ResponseEntity<?> phone(CustomerPhoneRequest request) {

    if (tempRepository.countByPhoneAndCreatedAtIsAfter(request.getPhone(),
        LocalDateTime.now().minusMinutes(
            CustomerConfigs.CODE_SAVING_MINUTES
        )) >= 1) {
      throw new BadRequestException(utils.getMessage(Message.MESSAGE_LIMIT_EXCEEDED));
    }

    var tempPhone = new TempCustomerModel();

    var code = utils.generateRandomNumber(CustomerConfigs.SMS_CODE_LENGTH);

    System.out.println(code);

    eskizSmsComponent.sendSms(new SmsRequest(
        request.getPhone(),
        "Bu Eskiz dan test"
    ));

    tempPhone.setPhone(request.getPhone());
    tempPhone.setCreatedAt(LocalDateTime.now());
    tempPhone.setSmsCode(code);

    tempRepository.save(tempPhone);


    return ResponseEntity.ok("done");
  }

  @Override
  public ResponseEntity<?> login(CustomerLoginRequest request) {

    var tempCustomer = tempRepository
        .findByPhoneAndSmsCodeAndCreatedAtAfter(
            request.getPhone(),
            request.getSmsCode(),
            LocalDateTime.now().minusMinutes(CustomerConfigs.CODE_SAVING_MINUTES)
        );

    if (tempCustomer.isEmpty()) {
      return ResponseEntity.badRequest().body(
          utils.getMessage(Message.MESSAGE_INVALID_CODE)
      );
    }

    var customer = repository.findByPhone(request.getPhone()).orElseGet(() -> {
      var newCustomer = new UserEntity();
      newCustomer.setPhone(request.getPhone());
      newCustomer.setRole(UserRoles.USER);
      newCustomer.setCreatedAt(LocalDateTime.now());
      newCustomer.setUsername(request.getPhone());
      return repository.save(newCustomer);
    });

//    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//        customer.getPhone(),
//        request.getSmsCode()
//    ));

    tempRepository.delete(tempCustomer.get());

    String token = jwtUtil.generateToken(customer);

    return ResponseEntity.ok("Bearer " + token);
  }

  @Override
  public UserEntity createSellerUser(CreateUserDto request) {

    if (repository.existsByUsername(request.getUsername())) {
      return null;
    }

    var user = UserEntity.builder()
        .username(request.getUsername())
        .firstName("seller-" + request.getUsername())
        .lastName("seller-" + request.getUsername())
        .role(UserRoles.SELLER)
        .createdAt(LocalDateTime.now())
        .build();

    return repository.save(user);
  }

  @Override
  public String loginWithUsername(String username) {
    return jwtUtil.generateToken(repository.findByUsername(username
    ).orElseThrow());
  }

  @Override
  public UserEntity getUserByUsername(String username) {
    return repository.findByUsername(username).orElseThrow();
  }

  @Override
  public UserEntity getUser(Principal principal) {
    return getUserByUsername(principal.getName());
  }

  @Override
  public void updateUserInfo(UserUpdateInfoDto request, Principal principal) {
    var user = getUser(principal);

    user.setFirstName(request.getFirstName());
    user.setLastName(request.getLastName());

    repository.save(user);
  }

  @Override
  public MinimumUserResponseDto getUserInfo(Principal principal) {
    var user = getUser(principal);

    return new MinimumUserResponseDto(
        user.getId(),
        user.getFirstName(),
        user.getLastName()
    );
  }
}
