package com.qiroldev.monomarket.product.product.sku.skuspecifications;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkuSpecificationsRepository extends
    JpaRepository<SkuSpecificationsEntity, Long> {

  List<SkuSpecificationsEntity> findAllBySpecificationIdInAndSkuProductId(List<Long> idList,
      Long productId);

}
