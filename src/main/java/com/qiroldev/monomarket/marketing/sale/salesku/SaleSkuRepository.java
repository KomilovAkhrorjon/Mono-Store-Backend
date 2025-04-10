package com.qiroldev.monomarket.marketing.sale.salesku;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleSkuRepository extends JpaRepository<SaleSkuEntity, Long> {

  List<SaleSkuEntity> findAllBySaleId(Long saleId);

}
