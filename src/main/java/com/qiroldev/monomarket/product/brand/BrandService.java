package com.qiroldev.monomarket.product.brand;

import com.qiroldev.monomarket.product.brand.dto.BrandDto;
import com.qiroldev.monomarket.product.brand.dto.BrandResponseDto;
import java.security.Principal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BrandService {

  void create(BrandDto request, Principal principal);

  void update(BrandDto request, Principal principal);

  Page<BrandResponseDto> get(Pageable pageable);

  Page<BrandResponseDto> find(String title, Pageable pageable);

  BrandEntity getBrandById(Long brandId);

  BrandResponseDto getDto(BrandEntity brand);
}
