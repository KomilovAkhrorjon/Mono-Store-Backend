package com.qiroldev.monomarket.order.ordersku;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderSkuRepository extends JpaRepository<OrderSkuEntity, Long> {

}
