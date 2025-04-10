package com.qiroldev.monomarket.marketing.sale;

import com.qiroldev.monomarket.marketing.sale.dto.SaveSaleRequestDto;
import com.qiroldev.monomarket.marketing.sale.media.SaleMediaEntity;
import com.qiroldev.monomarket.marketing.sale.media.SaleMediaRepository;
import com.qiroldev.monomarket.marketing.sale.salesku.SaleSkuEntity;
import com.qiroldev.monomarket.marketing.sale.salesku.SaleSkuService;
import com.qiroldev.monomarket.marketing.sale.salesku.dto.SaleSkuRequestDto;
import com.qiroldev.monomarket.marketing.sale.saletitle.SaleTitleEntity;
import com.qiroldev.monomarket.marketing.sale.saletitle.SaleTitleRepository;
import com.qiroldev.monomarket.product.product.sku.SkuService;
import com.qiroldev.monomarket.utils.Utils;
import com.qiroldev.monomarket.utils.exception.ResourceNotFoundException;
import com.qiroldev.monomarket.utils.message.Message;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

  private final SaleRepository saleRepository;
  private final SaleTitleRepository saleTitleRepository;
  private final SaleMediaRepository saleMediaRepository;
  private final Utils utils;
  private final SkuService skuService;
  private final SaleSkuService saleSkuService;

  @Override
  public SaleEntity createSale(
      SaveSaleRequestDto request,
      Principal principal
  ) {

    var newSale = new SaleEntity();

    newSale.setColor(request.getColor());

    var savedSale = saleRepository.save(newSale);

    for (var saleTitle : request.getTitles()) {
      var newSaleTitle = saleTitleRepository.save(
          new SaleTitleEntity(
              saleTitle.getTitle(),
              saleTitle.getDescription(),
              saleTitle.getLang(),
              savedSale
          )
      );

      for (var media : saleTitle.getMedias()) {
        saleMediaRepository.save(
            new SaleMediaEntity(
                media.getMediaKey(),
                media.getUrl(),
                media.getType(),
                media.getName(),
                media.getSavedName(),
                media.getSize(),
                newSaleTitle
            )
        );
      }
    }

    return saleRepository.findById(savedSale.getId()).orElseThrow(()
            -> new ResourceNotFoundException(
            savedSale.getId().toString(),
            List.of(
                utils.getMessage(Message.SALE_NOT_FOUND)
            )
        )
    );
  }

  @Override
  public List<SaleEntity> getAllSales() {
    return saleRepository.findAll();
  }

  @Override
  public List<SaleSkuEntity> assignSale(
      SaleSkuRequestDto request,
      Principal principal
  ) {
    var skuList = skuService.findByIdIn(request.getSkuId());
    var sale = saleRepository.findById(request.getSaleId()).orElseThrow(()
            -> new ResourceNotFoundException(
            request.getSaleId().toString(),
            List.of(
                utils.getMessage(Message.SALE_NOT_FOUND)
            )
        )
    );
    return saleSkuService.createSaleSku( skuList, sale);
  }

}
