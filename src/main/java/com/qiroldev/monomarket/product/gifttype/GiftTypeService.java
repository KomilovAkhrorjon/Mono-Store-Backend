package com.qiroldev.monomarket.product.gifttype;

import com.qiroldev.monomarket.product.gifttype.dto.GiftTypeDto;
import com.qiroldev.monomarket.product.gifttype.dto.GiftTypeFullResponseDto;
import com.qiroldev.monomarket.product.gifttype.dto.GiftTypeResponseDto;
import java.util.List;

public interface GiftTypeService {

  void create(GiftTypeDto request);

  void update(GiftTypeDto request);

  List<GiftTypeResponseDto> getAll();

  GiftTypeEntity getById(Long id);

  GiftTypeResponseDto toDto(GiftTypeEntity giftType);

  List<GiftTypeFullResponseDto> getFullData();
}
