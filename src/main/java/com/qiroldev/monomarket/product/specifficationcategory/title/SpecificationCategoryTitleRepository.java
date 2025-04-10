package com.qiroldev.monomarket.product.specifficationcategory.title;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecificationCategoryTitleRepository extends
    JpaRepository<SpecificationCategoryTitleEntity, Long> {

}
