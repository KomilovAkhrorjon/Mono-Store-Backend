package com.qiroldev.monomarket.product.specifficationcategory;

import com.qiroldev.monomarket.product.specifficationcategory.dto.SpecificationCategoryFilterParams;
import com.qiroldev.monomarket.product.specifficationcategory.dto.SpecificationCategoryFullResponseDto;
import com.qiroldev.monomarket.product.specifficationcategory.dto.SpecificationCategoryRequestDto;
import com.qiroldev.monomarket.product.specifficationcategory.dto.SpecificationCategoryResponseDto;
import java.security.Principal;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SpecificationCategoryService {

  void createSpecificationCategory(
      List<SpecificationCategoryRequestDto> specificationCategoryDto, Principal principal);

  void updateSpecificationCategory(
      List<SpecificationCategoryRequestDto> specificationCategoryDto,
      Boolean isActive,
      Long specificationCategoryId,
      Principal principal);


  Page<SpecificationCategoryResponseDto> getAllSpecificationCategories(
      SpecificationCategoryFilterParams filterParams, Pageable pageable);

  SpecificationCategoryEntity getById(Long categoryId);

  SpecificationCategoryResponseDto toDto(SpecificationCategoryEntity category);

  List<SpecificationCategoryFullResponseDto> getFull();
}
