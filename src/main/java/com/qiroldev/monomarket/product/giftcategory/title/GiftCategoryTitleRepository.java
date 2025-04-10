package com.qiroldev.monomarket.product.giftcategory.title;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftCategoryTitleRepository extends JpaRepository<GiftCategoryTitleModel, Long> {

}
