package com.qiroldev.monomarket.marketing.sale.media;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleMediaRepository extends JpaRepository<SaleMediaEntity, Long> {

}
