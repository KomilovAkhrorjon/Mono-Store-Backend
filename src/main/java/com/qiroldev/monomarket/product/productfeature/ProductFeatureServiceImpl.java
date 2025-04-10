package com.qiroldev.monomarket.product.productfeature;

import com.qiroldev.monomarket.product.feature.FeatureEntity;
import com.qiroldev.monomarket.product.product.ProductEntity;
import com.qiroldev.monomarket.utils.Utils;
import com.qiroldev.monomarket.utils.exception.ResourceNotFoundException;
import com.qiroldev.monomarket.utils.message.Message;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductFeatureServiceImpl implements ProductFeatureService {

  private final ProductFeatureRepository repository;
  private final Utils utils;

  @Override
  public ProductFeatureEntity createProductFeature(ProductFeatureEntity productFeatureEntity) {
    return repository.save(productFeatureEntity);
  }

  @Override
  public ProductFeatureEntity updateProductFeature(ProductFeatureEntity productFeatureEntity) {
    return repository.save(productFeatureEntity);
  }

  @Override
  public void deleteProductFeature(Long productFeatureId) {
    repository.deleteById(productFeatureId);
  }

  @Override
  public ProductFeatureEntity getProductFeature(Long productFeatureId) {
    return repository.findById(productFeatureId).orElseThrow(
        () -> new ResourceNotFoundException(
            productFeatureId.toString(),
            List.of(utils.getMessage(Message.PRODUCT_FEATURE_NOT_FOUND))
        )
    );
  }

  @Override
  public void attachProductFeatureToProduct(ProductEntity productEntity, List<FeatureEntity> featureEntities) {

    List<FeatureEntity> featuresToAttach = new ArrayList<>();

    if (productEntity.getFeatures() != null){
      var featuresToDelete = productEntity.getFeatures().stream().filter(
          productFeatureEntity -> !featureEntities.contains(productFeatureEntity.getFeature())
      ).toList();

      repository.deleteAll(featuresToDelete);

      featuresToAttach.addAll(featureEntities.stream().filter(
          featureEntity -> productEntity.getFeatures().stream().noneMatch(
              productFeatureEntity -> productFeatureEntity.getFeature().equals(featureEntity)
          )
      ).toList());
    } else {
      featuresToAttach.addAll(featureEntities);
    }



    var productFeatureEntityList = featuresToAttach.stream()
        .map(featureEntity -> {
          ProductFeatureEntity productFeatureEntity = new ProductFeatureEntity();
          productFeatureEntity.setProduct(productEntity);
          productFeatureEntity.setFeature(featureEntity);
          return productFeatureEntity;
        })
        .toList();

    repository.saveAll(productFeatureEntityList);
  }

}
