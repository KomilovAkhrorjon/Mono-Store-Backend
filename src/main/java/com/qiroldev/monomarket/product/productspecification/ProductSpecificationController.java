package com.qiroldev.monomarket.product.productspecification;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ProductSpecificationController implements ProductSpecificationApi {

  private final ProductSpecificationService service;

  @Override
  public ResponseEntity<List<ProductSpecificationEntity>> getAllProductSpecifications() {
    return ResponseEntity.ok(service.getAllProductSpecifications());
  }

}
