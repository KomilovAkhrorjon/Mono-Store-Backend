package com.qiroldev.monomarket.marketing.sale;

import com.qiroldev.monomarket.marketing.sale.dto.SaveSaleRequestDto;
import com.qiroldev.monomarket.marketing.sale.salesku.SaleSkuEntity;
import com.qiroldev.monomarket.marketing.sale.salesku.dto.SaleSkuRequestDto;
import jakarta.validation.Valid;
import java.security.Principal;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v0/sale")
public interface SaleApi {

  @PostMapping()
  ResponseEntity<SaleEntity> createSale(
      @RequestBody @Valid SaveSaleRequestDto request,
      Principal principal);

  @GetMapping()
  ResponseEntity<List<SaleEntity>> getAllSales();

  @PostMapping("/assign")
  ResponseEntity<List<SaleSkuEntity>> assignSale(
      @RequestBody @Valid SaleSkuRequestDto request,
      Principal principal);

}
