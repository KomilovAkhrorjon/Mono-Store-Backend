package com.qiroldev.monomarket.product.country;

import com.qiroldev.monomarket.product.country.dto.CountryDto;
import com.qiroldev.monomarket.product.country.dto.CountryFullResponseDto;
import com.qiroldev.monomarket.product.country.dto.CountryResponseDto;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v0/countries")
public interface CountryApi {

  @PostMapping()
  ResponseEntity<Void> create(@RequestBody @Valid CountryDto request);

  @PutMapping()
  ResponseEntity<Void> update(@RequestBody CountryDto request);

  @GetMapping()
  ResponseEntity<Page<CountryResponseDto>> getAll(Pageable pageable);

  @DeleteMapping()
  ResponseEntity<Void> delete(@RequestParam Long id);

  @GetMapping("/full")
  ResponseEntity<List<CountryFullResponseDto>> getAll();
}
