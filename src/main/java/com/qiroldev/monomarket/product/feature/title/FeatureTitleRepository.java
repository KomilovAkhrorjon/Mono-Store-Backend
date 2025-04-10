package com.qiroldev.monomarket.product.feature.title;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureTitleRepository extends JpaRepository<FeatureTitleEntity, Long> {

}
