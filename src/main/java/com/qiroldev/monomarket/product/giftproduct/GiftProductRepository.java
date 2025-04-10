package com.qiroldev.monomarket.product.giftproduct;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftProductRepository extends JpaRepository<GiftProductEntity, Long> {

  List<GiftProductEntity> findAllByGiftCategoryId(Long categoryId);

}
