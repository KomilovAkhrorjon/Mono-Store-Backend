package com.qiroldev.monomarket.product.product;

import com.qiroldev.monomarket.product.product.dto.ProductFilterParamsDto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

  @Query("""
    select case when count(p) > 0 then true else false end
      from ProductEntity p
      left join p.titles t
      left join p.country c
      where (:#{#filterParams.title} is null or t.title = :#{#filterParams.title})
      and (:#{#filterParams.countryId} is null or c.id = :#{#filterParams.countryId})
    """)
  Boolean existsByFilter(ProductFilterParamsDto filterParams);

  @Query("""
        select p
        from ProductEntity p
        where p.category.id in :#{#categoryIdList}
        and p.status = 'ACTIVE'
        """)
  Page<ProductEntity> findAllByCategoryIdList(
      List<Long> categoryIdList,
      Pageable pageable);

  @Query("""
        select p
        from ProductEntity p
        where p.id = :#{#id}
        and p.status = :#{#status}
        """)
  Optional<ProductEntity> findByIdAndStatusAndLang(Long id, ProductStatus status);

  List<ProductEntity> findAllByIdIn(List<Long> ids);
}
