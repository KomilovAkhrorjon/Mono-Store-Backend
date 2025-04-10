package com.qiroldev.monomarket.product.productspecification;

import com.qiroldev.monomarket.product.product.ProductEntity;
import com.qiroldev.monomarket.product.specification.SpecificationEntity;
import java.util.List;

public interface ProductSpecificationService {

  List<ProductSpecificationEntity> getAllProductSpecifications();

  ProductSpecificationEntity attachSpecification(
      ProductEntity productEntity,
      SpecificationEntity specificationEntity
  );

  void attachAllSpecification(ProductEntity savedProduct, List<SpecificationEntity> byIds);
}
