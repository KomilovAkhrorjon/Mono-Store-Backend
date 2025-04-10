package com.qiroldev.monomarket.product.productspecification;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v0/product-specification")
public interface ProductSpecificationApi {

  @GetMapping()
  ResponseEntity<List<ProductSpecificationEntity>> getAllProductSpecifications();

}
