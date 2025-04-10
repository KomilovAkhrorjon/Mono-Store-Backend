package com.qiroldev.monomarket.marketing.promocode;

import com.qiroldev.monomarket.marketing.promocode.dto.PromoCodeResultDto;

public interface PromoCodeService {

  PromoCodeResultDto calculateByCode(String codes, Double total);
}
