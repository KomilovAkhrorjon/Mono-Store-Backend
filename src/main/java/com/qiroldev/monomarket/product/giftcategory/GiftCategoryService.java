package com.qiroldev.monomarket.product.giftcategory;

import com.qiroldev.monomarket.product.giftcategory.dto.GiftCategoryFilterParams;
import com.qiroldev.monomarket.product.giftcategory.dto.GiftCategoryFullResponseDto;
import com.qiroldev.monomarket.product.giftcategory.dto.GiftCategoryRequestDto;
import com.qiroldev.monomarket.product.giftcategory.dto.GiftCategoryResponseDto;
import java.security.Principal;
import java.util.List;

public interface GiftCategoryService {

  void create(GiftCategoryRequestDto request, Principal principal);

  void update(GiftCategoryRequestDto request, Principal principal);

  void delete(Long id, Principal principal);

  List<GiftCategoryResponseDto> getAll(GiftCategoryFilterParams filterParams);

  List<GiftCategoryFullResponseDto> getFullResponse(GiftCategoryFilterParams filterParams);

  GiftCategoryEntity getById(Long giftCategoryId);

  List<GiftCategoryEntity> findAllByType(String giftType);

  GiftCategoryResponseDto toDto(GiftCategoryEntity model);
}
