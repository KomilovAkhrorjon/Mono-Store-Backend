package com.qiroldev.monomarket.external.warehouse;

import com.qiroldev.monomarket.external.warehouse.dto.ProductPriceResponseDto;
import com.qiroldev.monomarket.external.warehouse.dto.SkuPriceResponseDto;
import com.qiroldev.monomarket.external.warehouse.dto.WarehouseOrderRequestDto;
import com.qiroldev.monomarket.external.warehouse.dto.WarehouseOrderResponseDto;
import java.util.List;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "file-warehouse-service", url = "localhost:4401")
public interface WarehouseClient {

  @PostMapping("/products/market/product")
  List<ProductPriceResponseDto> getProductStocks(
      @RequestHeader Map<String, String> headers,
      @RequestBody List<Long> productIds
  );

  @PostMapping("/products/market/sku")
  List<SkuPriceResponseDto> getSkuInfo(
      @RequestHeader Map<String, String> headers,
      @RequestBody List<Long> productIds
  );

  @PostMapping("/orders/market")
  void createOrder(
      @RequestHeader Map<String, String> headers,
      @RequestBody WarehouseOrderRequestDto request);

  @GetMapping("/orders/market")
  List<WarehouseOrderResponseDto> getOrders(
      @RequestHeader Map<String, String> headers,
      @RequestParam String clientPhone
  );

  @GetMapping("/orders/market/{orderId}")
  WarehouseOrderResponseDto getOrderById(
      @RequestHeader Map<String, String> headers,
      @PathVariable Long orderId);
}
