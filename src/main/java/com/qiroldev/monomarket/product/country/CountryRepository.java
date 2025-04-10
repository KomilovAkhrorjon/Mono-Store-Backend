package com.qiroldev.monomarket.product.country;

import com.qiroldev.monomarket.product.country.dto.CountryResponseDto;
import com.qiroldev.monomarket.product.system.enums.Lang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Long> {

  @Query("""
            select new com.qiroldev.monomarket.product.country.dto.CountryResponseDto(
              c.id,
              c.code,
              t.title
            )
            from CountryEntity c
            left join c.titles t on t.lang = :lang
      """)
  Page<CountryResponseDto> findByPageAndDto(Lang lang, Pageable pageable);
}