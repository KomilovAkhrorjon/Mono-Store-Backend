package com.qiroldev.monomarket.product.product.sku;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkuRepository extends JpaRepository<SkuEntity, Long> {

  List<SkuEntity> findAllByProductId(Long productId);

  List<SkuEntity> findByIdIn(List<Long> skuIds);

  List<SkuEntity> findAllByWarehouseIdIn(List<Long> warehouseIds);
}
