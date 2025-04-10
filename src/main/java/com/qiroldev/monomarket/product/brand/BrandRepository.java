package com.qiroldev.monomarket.product.brand;

import com.qiroldev.monomarket.product.brand.dto.BrandResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

  @Query("""
            select new com.qiroldev.monomarket.product.brand.dto.BrandResponseDto(
              b.id,
              b.title,
              b.logo,
              b.titleLink,
              b.createdAt
            )
            from BrandEntity b
      """)
  Page<BrandResponseDto> findAllAndDto(Pageable pageable);

  @Query("""
            select new com.qiroldev.monomarket.product.brand.dto.BrandResponseDto(
              b.id,
              b.title,
              b.logo,
              b.titleLink,
              b.createdAt
            )
            from BrandEntity b
            where b.title ilike %:title%
      """)
  Page<BrandResponseDto> findAllByTitle(String title, Pageable pageable);
}