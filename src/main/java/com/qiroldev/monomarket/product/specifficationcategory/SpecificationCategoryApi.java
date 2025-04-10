package com.qiroldev.monomarket.product.specifficationcategory;

import com.qiroldev.monomarket.product.specifficationcategory.dto.SpecificationCategoryFilterParams;
import com.qiroldev.monomarket.product.specifficationcategory.dto.SpecificationCategoryFullResponseDto;
import com.qiroldev.monomarket.product.specifficationcategory.dto.SpecificationCategoryRequestDto;
import com.qiroldev.monomarket.product.specifficationcategory.dto.SpecificationCategoryResponseDto;
import java.security.Principal;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v0/specification-categories")
public interface SpecificationCategoryApi {

  @PostMapping()
  ResponseEntity<Void> createSpecificationCategory(
      @RequestBody List<SpecificationCategoryRequestDto> specificationCategoryDto,
      Principal principal);

  @PutMapping()
  ResponseEntity<Void> updateSpecificationCategory(
      @RequestBody List<SpecificationCategoryRequestDto> specificationCategoryDto,
      @RequestParam Boolean isActive,
      @RequestParam Long specificationCatalogId,
      Principal principal);

  @GetMapping()
  ResponseEntity<Page<SpecificationCategoryResponseDto>> getAllSpecificationCategories(
      SpecificationCategoryFilterParams filterParams,
      Pageable pageable
  );

  @GetMapping("/full")
  ResponseEntity<List<SpecificationCategoryFullResponseDto>> getFullResponse();
}
