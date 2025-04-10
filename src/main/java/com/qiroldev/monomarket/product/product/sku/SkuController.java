package com.qiroldev.monomarket.product.product.sku;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SkuController {

  private final SkuServiceImpl skuService;

}
