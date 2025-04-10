package com.qiroldev.monomarket.product.productfeature;

import com.qiroldev.monomarket.product.feature.FeatureEntity;
import com.qiroldev.monomarket.product.product.ProductEntity;
import java.util.List;

public interface ProductFeatureService {

  ProductFeatureEntity createProductFeature(ProductFeatureEntity productFeatureEntity);

  ProductFeatureEntity updateProductFeature(ProductFeatureEntity productFeatureEntity);

  void deleteProductFeature(Long productFeatureId);

  ProductFeatureEntity getProductFeature(Long productFeatureId);

  void attachProductFeatureToProduct(ProductEntity productEntity,
      List<FeatureEntity> featureEntities);
}
