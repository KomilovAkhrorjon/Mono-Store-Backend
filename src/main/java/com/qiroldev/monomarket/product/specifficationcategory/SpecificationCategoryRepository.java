package com.qiroldev.monomarket.product.specifficationcategory;

import com.qiroldev.monomarket.product.specifficationcategory.dto.SpecificationCategoryFilterParams;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecificationCategoryRepository extends
    JpaRepository<SpecificationCategoryEntity, Long> {

  Optional<SpecificationCategoryEntity> findByIdAndCreatedById(Long id, Long createdById);

  @Query("""
      select s from SpecificationCategoryEntity s
      left join s.titles t
      where (:#{#filterParams.title} is null or t.title ilike %:#{#filterParams.title}%)
  """)
  Page<SpecificationCategoryEntity> findAllByFilterParams(
      SpecificationCategoryFilterParams filterParams,
      Pageable pageable);
}
