package com.qiroldev.monomarket.product.gifttype;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiftTypeRepository extends JpaRepository<GiftTypeEntity, Long> {

  List<GiftTypeEntity> findAllByIsActiveIsTrue();

}
