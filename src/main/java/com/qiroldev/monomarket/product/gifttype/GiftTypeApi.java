package com.qiroldev.monomarket.product.gifttype;

import com.qiroldev.monomarket.product.gifttype.dto.GiftTypeDto;
import com.qiroldev.monomarket.product.gifttype.dto.GiftTypeFullResponseDto;
import com.qiroldev.monomarket.product.gifttype.dto.GiftTypeResponseDto;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v0/gift-type")
public interface GiftTypeApi {

  @PostMapping()
  ResponseEntity<Void> create(@RequestBody @Valid GiftTypeDto request);

  @PutMapping()
  ResponseEntity<Void> update(@RequestBody @Valid GiftTypeDto request);

  @GetMapping()
  ResponseEntity<List<GiftTypeResponseDto>> get();

  @GetMapping("/full")
  ResponseEntity<List<GiftTypeFullResponseDto>> getFull();

}
