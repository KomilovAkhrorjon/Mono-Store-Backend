package com.qiroldev.monomarket.product.gifttype;

import com.qiroldev.monomarket.product.gifttype.dto.GiftTypeDto;
import com.qiroldev.monomarket.product.gifttype.dto.GiftTypeFullResponseDto;
import com.qiroldev.monomarket.product.gifttype.dto.GiftTypeResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class GiftTypeController implements GiftTypeApi {

  private final GiftTypeService giftTypeService;

  @Override
  public ResponseEntity<Void> create(GiftTypeDto request) {
    giftTypeService.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  public ResponseEntity<Void> update(GiftTypeDto request) {
    giftTypeService.update(request);

    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  @Override
  public ResponseEntity<List<GiftTypeResponseDto>> get() {
    return ResponseEntity.ok(giftTypeService.getAll());
  }

  @Override
  public ResponseEntity<List<GiftTypeFullResponseDto>> getFull() {
    return ResponseEntity.ok(giftTypeService.getFullData());
  }

}
