package com.qiroldev.monomarket.marketing.sale.saletitle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleTitleRepository extends JpaRepository<SaleTitleEntity, Long> {

}
