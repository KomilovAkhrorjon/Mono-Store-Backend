package com.qiroldev.monomarket.product.specification.title;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecificationTitleRepository extends
    JpaRepository<SpecificationTitleEntity, Long> {

}
