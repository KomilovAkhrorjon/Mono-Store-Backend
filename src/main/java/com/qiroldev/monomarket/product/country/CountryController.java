package com.qiroldev.monomarket.product.country;

import com.qiroldev.monomarket.product.country.dto.CountryDto;
import com.qiroldev.monomarket.product.country.dto.CountryFullResponseDto;
import com.qiroldev.monomarket.product.country.dto.CountryResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CountryController implements CountryApi {

  private final CountryService service;

  @Override
  public ResponseEntity<Void> create(CountryDto request) {
    service.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  public ResponseEntity<Void> update(@RequestBody CountryDto request) {
    service.update(request);
    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  @Override
  public ResponseEntity<Page<CountryResponseDto>> getAll(Pageable pageable) {
    return ResponseEntity.ok(service.allCountries(pageable));
  }

  @Override
  public ResponseEntity<Void> delete(@RequestParam Long id) {
    service.delete(id);
    return ResponseEntity.status(HttpStatus.GONE).build();
  }

  @Override
  public ResponseEntity<List<CountryFullResponseDto>> getAll() {
    return ResponseEntity.ok(service.fullCountries());
  }

}
