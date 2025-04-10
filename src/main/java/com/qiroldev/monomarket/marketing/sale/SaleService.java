package com.qiroldev.monomarket.marketing.sale;

import com.qiroldev.monomarket.marketing.sale.dto.SaveSaleRequestDto;
import com.qiroldev.monomarket.marketing.sale.salesku.SaleSkuEntity;
import com.qiroldev.monomarket.marketing.sale.salesku.dto.SaleSkuRequestDto;
import java.security.Principal;
import java.util.List;

public interface SaleService {

  SaleEntity createSale(SaveSaleRequestDto request, Principal principal);

  List<SaleEntity> getAllSales();

  List<SaleSkuEntity> assignSale(SaleSkuRequestDto request, Principal principal);
}
