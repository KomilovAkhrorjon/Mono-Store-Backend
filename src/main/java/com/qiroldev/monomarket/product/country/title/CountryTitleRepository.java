package com.qiroldev.monomarket.product.country.title;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

@Controller
public interface CountryTitleRepository extends JpaRepository<CountryTitleModel, Long> {

  void deleteByCountryId(Long countryId);

}
