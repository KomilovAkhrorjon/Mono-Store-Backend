package com.qiroldev.monomarket.accounting.seller;

import com.qiroldev.monomarket.accounting.seller.dto.SellerCreateDto;
import com.qiroldev.monomarket.accounting.seller.dto.SellerLoginRequest;
import com.qiroldev.monomarket.accounting.seller.dto.SellerUpdateDto;
import com.qiroldev.monomarket.accounting.user.dto.CreateUserDto;
import com.qiroldev.monomarket.accounting.user.UserService;
import com.qiroldev.monomarket.utils.Utils;
import com.qiroldev.monomarket.utils.exception.BadRequestException;
import com.qiroldev.monomarket.utils.message.Message;
import java.security.Principal;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

  private final SellerRepository sellerRepository;
  private final UserService userService;
  private final Utils utils;
  private final PasswordEncoder passwordEncoder;


  @Override
  public ResponseEntity<?> create(SellerCreateDto request) {

    var user = userService.createSellerUser(
        new CreateUserDto(request.getUsername(), request.getPassword())
    );

    if (user == null) {
      return ResponseEntity.badRequest().body(utils.getMessage(Message.USER_ALREADY_EXISTS));
    }

    var seller = new SellerModel(
        request.getTitle(),
        user,
        passwordEncoder.encode(request.getPassword()),
        LocalDateTime.now()
    );

    try {
      return ResponseEntity.ok(sellerRepository.save(seller));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(utils.getMessage(Message.SOMETHING_WENT_WRONG));
    }
  }

  @Override
  public ResponseEntity<?> login(SellerLoginRequest request) {

    var res = sellerRepository.findByUserUsername(request.getUsername());

    if (res.isEmpty()) {
      throw new BadRequestException(utils.getMessage(Message.SOMETHING_WENT_WRONG));
    }

    var seller = res.get();

    if (!passwordEncoder.matches(request.getPassword(), seller.getPassword())) {
      return ResponseEntity.badRequest().body(utils.getMessage(Message.SOMETHING_WENT_WRONG));
    }
    return ResponseEntity.ok("Bearer " + userService.loginWithUsername(request.getUsername()));
  }

  @Override
  public ResponseEntity<?> update(SellerUpdateDto request, Principal principal) {

    var res = sellerRepository.findByUserUsername(principal.getName());

    if (res.isEmpty()) {
      return ResponseEntity.badRequest().body(utils.getMessage(Message.SOMETHING_WENT_WRONG));
    }

    var seller = res.get();

    seller.setTitle(request.getTitle());
    seller.setUpdatedAt(LocalDateTime.now());
    seller.setDescriptionUz(request.getDescriptionUz());
    seller.setDescriptionRu(request.getDescriptionRu());
    seller.setDescriptionEn(request.getDescriptionEn());
    seller.setLogo(request.getLogo());
    seller.setBanner(request.getBanner());
    seller.setPhone(request.getPhone());

    try {
      return ResponseEntity.ok(sellerRepository.save(seller));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(utils.getMessage(Message.SOMETHING_WENT_WRONG));
    }
  }

  @Override
  public SellerModel getUserByUsername(String username) {
    return sellerRepository.findByUserUsername(username).orElseThrow(
        () -> new BadRequestException(utils.getMessage(Message.SELLER_NOT_FOUND))
    );
  }
}
