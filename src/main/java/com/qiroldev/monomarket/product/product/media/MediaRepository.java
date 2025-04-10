package com.qiroldev.monomarket.product.product.media;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends JpaRepository<MediaEntity, Long> {

  List<MediaEntity> findAllByProductId(Long productId);

}
