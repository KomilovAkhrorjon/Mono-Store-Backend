package com.qiroldev.monomarket.product.specification;

import com.qiroldev.monomarket.product.specification.dto.SpecificationFilterParams;
import com.qiroldev.monomarket.product.specification.dto.SpecificationFullResponseDto;
import com.qiroldev.monomarket.product.specification.dto.SpecificationRequestDto;
import com.qiroldev.monomarket.product.specification.dto.SpecificationResponseDto;
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

@RequestMapping("/v0/specification")
public interface SpecificationApi {

  @PostMapping()
  ResponseEntity<Void> createSpecification(
      @RequestBody List<SpecificationRequestDto> specificationRequestDto,
      @RequestParam Long categoryId,
      Principal principal);

  @PutMapping()
  ResponseEntity<Void> updateSpecification(
      @RequestBody List<SpecificationRequestDto> specificationRequestDto,
      @RequestParam Long specificationId,
      Principal principal);

  @GetMapping()
  ResponseEntity<Page<SpecificationResponseDto>> getAllSpecifications(
      SpecificationFilterParams filterParams, Pageable pageable);

  @GetMapping("/full")
  ResponseEntity<List<SpecificationFullResponseDto>> getFull(SpecificationFilterParams filterParams);

}
