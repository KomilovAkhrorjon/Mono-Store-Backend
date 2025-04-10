package com.qiroldev.monomarket.product.gifttype.title;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftTypeTitleRepository extends JpaRepository<GiftTypeTitleModel, Long> {

}
