package com.qiroldev.monomarket.marketing.promocode;

import com.qiroldev.monomarket.marketing.promocode.dto.PromoCodeResultDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PromoCodeServiceImpl implements PromoCodeService {

  private final PromoCodeRepository promoCodeRepository;

  @Override
  public PromoCodeResultDto calculateByCode(String codes, Double total) {

    var promoCodes = codes.split(",");

    Double totalDiscount = 0.0;

    for (String code : promoCodes) {
      var promoCode = promoCodeRepository.findByCode(code);
      if (promoCode != null) {
        totalDiscount += promoCode.getDiscount();
      }
    }

    return new PromoCodeResultDto(
        total - ((total * totalDiscount) / 100),
        totalDiscount
    );
  }

}
