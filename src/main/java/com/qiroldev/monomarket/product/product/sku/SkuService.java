package com.qiroldev.monomarket.product.product.sku;

import com.qiroldev.monomarket.product.product.ProductEntity;
import com.qiroldev.monomarket.product.product.sku.dto.SkuCreateRequestDto;
import java.util.List;

public interface SkuService {

  void createOrUpdate(ProductEntity product,
      List<SkuCreateRequestDto> skuCreateRequestDtos);

  SkuEntity findById(Long skuId);

  List<SkuEntity> findByIdIn(List<Long> skuIds);

  void deleteSpecificationsFromSku(Long productId, List<Long> specificationIds);

  void prepareSkuListAfterAddingSpecifications(ProductEntity savedProduct);

  List<SkuEntity> findAllByWarehouseIdList(List<Long> warehouseIds);
}
