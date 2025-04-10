package com.qiroldev.monomarket.product.country;

import com.qiroldev.monomarket.product.country.dto.CountryDto;
import com.qiroldev.monomarket.product.country.dto.CountryFullResponseDto;
import com.qiroldev.monomarket.product.country.dto.CountryResponseDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CountryService {

  void create(CountryDto request);

  void update(CountryDto request);

  Page<CountryResponseDto> allCountries(Pageable pageable);

  CountryEntity getCountryById(Long countryId);

  void delete(Long id);

  List<CountryFullResponseDto> fullCountries();
}
