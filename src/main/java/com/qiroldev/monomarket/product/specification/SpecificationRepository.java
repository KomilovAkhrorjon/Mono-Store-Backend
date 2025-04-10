package com.qiroldev.monomarket.product.specification;

import com.qiroldev.monomarket.product.specification.dto.SpecificationFilterParams;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecificationRepository extends JpaRepository<SpecificationEntity, Long> {

  @Query("""
      select s
      from SpecificationEntity s
      left join fetch s.titles t
      where (:#{#filterParams.specificationCategoryId} is null or s.category.id = :#{#filterParams.specificationCategoryId})
      and (:#{#filterParams.title} is null or t.title ilike %:#{#filterParams.title}%)
  """)
  Page<SpecificationEntity> findAllByFilterParams(SpecificationFilterParams filterParams, Pageable pageable);

  @Query("""
      select s
      from SpecificationEntity s
      left join fetch s.titles t
      where (:#{#filterParams.specificationCategoryId} is null or s.category.id = :#{#filterParams.specificationCategoryId})
      and (:#{#filterParams.title} is null or t.title ilike %:#{#filterParams.title}%)
  """)
  List<SpecificationEntity> findAllByFilterParams(SpecificationFilterParams filterParams);

  List<SpecificationEntity> findAllByIdIn(List<Long> categoryId);
}
