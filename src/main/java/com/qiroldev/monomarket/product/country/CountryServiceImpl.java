package com.qiroldev.monomarket.product.country;

import com.qiroldev.monomarket.product.country.dto.CountryDto;
import com.qiroldev.monomarket.product.country.dto.CountryFullResponseDto;
import com.qiroldev.monomarket.product.country.dto.CountryResponseDto;
import com.qiroldev.monomarket.product.country.dto.CountryTitlesDto;
import com.qiroldev.monomarket.product.country.title.CountryTitleModel;
import com.qiroldev.monomarket.product.country.title.CountryTitleRepository;
import com.qiroldev.monomarket.utils.Utils;
import com.qiroldev.monomarket.utils.exception.BadRequestException;
import com.qiroldev.monomarket.utils.message.Message;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

  private final CountryRepository countryRepository;
  private final CountryTitleRepository countryTitleRepository;
  private final Utils utils;
  private final CountryMapper countryMapper;

  @Override
  public void create(CountryDto request) {

    var country = new CountryEntity(request.getCode());

    try {
      var savedCountry = countryRepository.save(country);

      if (request.getTitles() != null && !request.getTitles().isEmpty()) {
        saveCountryTitle(savedCountry, request.getTitles());
      }
    } catch (Exception e) {
      throw new BadRequestException(utils.getMessage(Message.DUBLICATION_FOUNDED));
    }
  }

  @Override
  public void update(CountryDto request) {

    var country = countryRepository.findById(request.getId()).orElse(null);
    if (country == null) {
      throw new BadRequestException(utils.getMessage(Message.NOT_FOUND_EXCEPTION));
    }

    try {
      country.setCode(request.getCode());

      countryRepository.save(country);

      if (request.getTitles() != null && !request.getTitles().isEmpty()) {
        updateCountryTitle(country, request.getTitles());
      }

    } catch (Exception e) {
      throw new BadRequestException(utils.getMessage(Message.DUBLICATION_FOUNDED));
    }

  }

  @Override
  public Page<CountryResponseDto> allCountries(Pageable pageable) {
    var lang = utils.getLang();
    return countryRepository.findByPageAndDto(lang, pageable);
  }

  @Override
  public CountryEntity getCountryById(Long countryId) {
    return countryRepository.findById(countryId).orElseThrow(() ->
        new BadRequestException(utils.getMessage(Message.COUNTRY_NOT_FOUND_EXCEPTION))
    );
  }

  @Override
  @Transactional
  public void delete(Long id) {

    var country = countryRepository.findById(id).orElse(null);
    if (country == null) {
      throw new BadRequestException(utils.getMessage(Message.COUNTRY_NOT_FOUND_EXCEPTION));
    }

    try {

      countryTitleRepository.deleteByCountryId(country.getId());
      countryRepository.delete(country);
    } catch (Exception e) {
      throw new BadRequestException(utils.getMessage(Message.COUNTRY_HAS_PRODUCTS));
    }
  }

  @Override
  public List<CountryFullResponseDto> fullCountries() {
    return countryRepository.findAll().stream().map(countryMapper::toFullResponseDto).toList();
  }

  private void saveCountryTitle(CountryEntity country, List<CountryTitlesDto> request) {

    var titleList = new ArrayList<CountryTitleModel>();

    for (var dto : request) {
      var title = new CountryTitleModel();
      title.setCountry(country);
      title.setTitle(dto.getTitle());
      title.setLang(dto.getLang());
      titleList.add(title);
    }

    countryTitleRepository.saveAll(titleList);
  }

  private void updateCountryTitle(CountryEntity country, List<CountryTitlesDto> request) {
    var titleList = new ArrayList<CountryTitleModel>();

    for (var dto : request) {

      try {
        var title = countryTitleRepository.findById(dto.getId()).orElse(null);
        if (title == null) {
          continue;
        }
        title.setTitle(dto.getTitle());
        title.setLang(dto.getLang());
        titleList.add(title);
      } catch (Exception e) {
        titleList.add(new
            CountryTitleModel(country, dto.getTitle(), dto.getLang()
        ));
        log.error("Error while updating country title", e);
      }
    }

    countryTitleRepository.saveAll(titleList);
  }
}
