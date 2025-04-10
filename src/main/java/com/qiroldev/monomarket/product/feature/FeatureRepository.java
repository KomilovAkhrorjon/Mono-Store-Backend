package com.qiroldev.monomarket.product.feature;

import com.qiroldev.monomarket.product.feature.dto.FeatureResponseDto;
import com.qiroldev.monomarket.product.system.enums.Lang;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureRepository extends JpaRepository<FeatureEntity, Long> {

  List<FeatureEntity> findByIdIn(List<Long> featureIds);

  @Query("""
            select new com.qiroldev.monomarket.product.feature.dto.FeatureResponseDto(
              f.id,
              t.title,
              t.description,
              f.createdAt
            )
            from FeatureEntity f
            left join f.titles t on t.lang = :lang
        """)
  Page<FeatureResponseDto> findAllWithDto(Lang lang, Pageable pageable);
}
