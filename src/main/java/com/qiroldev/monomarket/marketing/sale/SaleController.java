package com.qiroldev.monomarket.marketing.sale;

import com.qiroldev.monomarket.marketing.sale.dto.SaveSaleRequestDto;
import com.qiroldev.monomarket.marketing.sale.salesku.SaleSkuEntity;
import com.qiroldev.monomarket.marketing.sale.salesku.dto.SaleSkuRequestDto;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SaleController implements SaleApi {

    private final SaleService saleService;

  @Override
  public ResponseEntity<SaleEntity> createSale(SaveSaleRequestDto request, Principal principal) {
    return ResponseEntity.ok(saleService.createSale(request, principal));
  }

  @Override
  public ResponseEntity<List<SaleEntity>> getAllSales() {
    return ResponseEntity.ok(saleService.getAllSales());
  }

  @Override
  public ResponseEntity<List<SaleSkuEntity>> assignSale(SaleSkuRequestDto request, Principal principal) {
    return ResponseEntity.ok(saleService.assignSale(request, principal));
  }
}
