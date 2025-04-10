package com.qiroldev.monomarket.product.specifficationcategory;

import com.qiroldev.monomarket.product.specifficationcategory.dto.SpecificationCategoryFilterParams;
import com.qiroldev.monomarket.product.specifficationcategory.dto.SpecificationCategoryFullResponseDto;
import com.qiroldev.monomarket.product.specifficationcategory.dto.SpecificationCategoryRequestDto;
import com.qiroldev.monomarket.product.specifficationcategory.dto.SpecificationCategoryResponseDto;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SpecificationCategoryController implements SpecificationCategoryApi{

  private final SpecificationCategoryService service;


  @Override
  public ResponseEntity<Void> createSpecificationCategory(
      List<SpecificationCategoryRequestDto> specificationCategoryDto, Principal principal) {
    service.createSpecificationCategory(specificationCategoryDto, principal);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  public ResponseEntity<Void> updateSpecificationCategory(
      List<SpecificationCategoryRequestDto> specificationCategoryDto,
      Boolean isActive,
      Long specificationCatalogId,
      Principal principal) {
    service.updateSpecificationCategory(
        specificationCategoryDto,
        isActive,
        specificationCatalogId,
        principal);
    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  @Override
  public ResponseEntity<Page<SpecificationCategoryResponseDto>> getAllSpecificationCategories(
      SpecificationCategoryFilterParams filterParams,
      Pageable pageable
  ) {
    return ResponseEntity.ok(service.getAllSpecificationCategories(filterParams, pageable));
  }

  @Override
  public ResponseEntity<List<SpecificationCategoryFullResponseDto>> getFullResponse() {
    return ResponseEntity.ok(service.getFull());
  }
}
