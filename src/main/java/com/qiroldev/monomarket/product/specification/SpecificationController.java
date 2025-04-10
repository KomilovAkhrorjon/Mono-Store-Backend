package com.qiroldev.monomarket.product.specification;

import com.qiroldev.monomarket.product.specification.dto.SpecificationFilterParams;
import com.qiroldev.monomarket.product.specification.dto.SpecificationFullResponseDto;
import com.qiroldev.monomarket.product.specification.dto.SpecificationRequestDto;
import com.qiroldev.monomarket.product.specification.dto.SpecificationResponseDto;
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
public class SpecificationController implements SpecificationApi {

  private final SpecificationService service;

  @Override
  public ResponseEntity<Void> createSpecification(
      List<SpecificationRequestDto> specificationRequestDto,
      Long categoryId,
      Principal principal) {
    service.createSpecification(specificationRequestDto, categoryId, principal);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  public ResponseEntity<Void> updateSpecification(
      List<SpecificationRequestDto> specificationRequestDto, Long specificationId, Principal principal) {
    service.update(specificationRequestDto, specificationId, principal);
    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  @Override
  public ResponseEntity<Page<SpecificationResponseDto>> getAllSpecifications(
      SpecificationFilterParams filterParams,
      Pageable pageable
  ) {
    return ResponseEntity.ok(service.getAllSpecifications(
        filterParams,
        pageable
    ));
  }

  @Override
  public ResponseEntity<List<SpecificationFullResponseDto>> getFull(
      SpecificationFilterParams filterParams) {
    return ResponseEntity.ok(service.getFull(filterParams));
  }
}
