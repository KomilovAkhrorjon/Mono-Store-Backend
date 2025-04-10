package com.qiroldev.monomarket.product.productspecification;

import com.qiroldev.monomarket.product.product.ProductEntity;
import com.qiroldev.monomarket.product.product.sku.SkuService;
import com.qiroldev.monomarket.product.specification.SpecificationEntity;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductSpecificationServiceImpl implements ProductSpecificationService {

  private final ProductSpecificationRepository repository;
  private final SkuService skuService;

  @Override
  public List<ProductSpecificationEntity> getAllProductSpecifications() {
    return repository.findAll();
  }

  @Override
  public ProductSpecificationEntity attachSpecification(
      ProductEntity productEntity,
      SpecificationEntity specificationEntity
  ) {
    return repository.save(new ProductSpecificationEntity(
        productEntity,
        specificationEntity
    ));
  }

  @Override
  public void attachAllSpecification(ProductEntity savedProduct, List<SpecificationEntity> byIds) {

    List<SpecificationEntity> specificationsToAttach = new ArrayList<>();

    if (savedProduct.getSpecifications() != null){
      var specificationsToDelete = savedProduct.getSpecifications()
          .stream()
          .filter(productSpecificationEntity -> !byIds.contains(
              productSpecificationEntity.getSpecification())).toList();

      if (!specificationsToDelete.isEmpty()){

        skuService.deleteSpecificationsFromSku(savedProduct.getId(),
            specificationsToDelete
                .stream()
                .map(s -> s.getSpecification().getId())
                .toList());

        repository.deleteAll(specificationsToDelete);
      }

      byIds.stream()
          .filter(specificationEntity -> savedProduct.getSpecifications().stream()
              .noneMatch(productSpecificationEntity -> productSpecificationEntity.getSpecification()
                  .equals(specificationEntity)))
          .forEach(specificationsToAttach::add);
    } else {
      specificationsToAttach = byIds;
    }

    repository.saveAll(specificationsToAttach.stream().map(specificationEntity -> new ProductSpecificationEntity(
        savedProduct,
        specificationEntity
    )).toList());
  }


}
