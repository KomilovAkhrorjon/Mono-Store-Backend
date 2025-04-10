package com.qiroldev.monomarket.external.warehouse;

import com.qiroldev.monomarket.external.warehouse.dto.ProductPriceResponseDto;
import com.qiroldev.monomarket.external.warehouse.dto.SkuPriceResponseDto;
import com.qiroldev.monomarket.external.warehouse.dto.WarehouseOrderRequestDto;
import com.qiroldev.monomarket.external.warehouse.dto.WarehouseOrderResponseDto;
import com.qiroldev.monomarket.utils.exception.BadRequestException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WarehouseComponent {

  private final WarehouseClient warehouseClient;

  private Map<String, String> getHeaders() {
    return Map.of(
        "Authorization",
        "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtb25vYWhyb3IiLCJhdXRob3JpdGllcyI6W3siYXV0aG9yaXR5Ijoib3JkZXI6d3JpdGUifSx7ImF1dGhvcml0eSI6InN5c3RlbTpyZWFkIn0seyJhdXRob3JpdHkiOiJwcm9kdWN0OndyaXRlIn0seyJhdXRob3JpdHkiOiJvcmRlcjpyZWFkIn0seyJhdXRob3JpdHkiOiJzeXN0ZW06d3JpdGUifSx7ImF1dGhvcml0eSI6IkFETUlOIn0seyJhdXRob3JpdHkiOiJwcm9kdWN0OnJlYWQifV0sImlhdCI6MTc0MTg1MjU4MywiZXhwIjoxNzQ3MDM2NTgzfQ.OKO3zjigK9F4lLAi6Fa4JBp2vwXiVEwegphd-GJi9cTuSxuBkC1kfUvmITzyNEq9W6E7MWItV7CmLMc8dWP78g"
    );
  }

  public Map<Long, ProductPriceResponseDto> getProductPrices(List<Long> productIds) {
    try {
      var res = warehouseClient.getProductStocks(getHeaders(), productIds);
      return res.stream().collect(
          Collectors.toMap(
              ProductPriceResponseDto::getProductId,
              Function.identity()
          )
      );
    } catch (Exception e) {
      throw new BadRequestException("warehouse.product.price.error");
    }
  }

  public Map<Long, SkuPriceResponseDto> getSkutPrices(List<Long> productIds) {
    try {
      return warehouseClient.getSkuInfo(getHeaders(), productIds).stream().collect(
          Collectors.toMap(
              SkuPriceResponseDto::getSkuId,
              Function.identity()
          )
      );
    } catch (Exception e) {
      throw new BadRequestException("warehouse.sku.price.error");
    }
  }

  public SkuPriceResponseDto getSkutPrice(Long skuId) {
    try {
      return warehouseClient.getSkuInfo(getHeaders(), List.of(skuId)).get(0);
    }catch (Exception e) {
      throw new BadRequestException("warehouse.sku.price.error");
    }
  }

  public void createOrder(WarehouseOrderRequestDto request) {
    try {
      warehouseClient.createOrder(getHeaders(), request);
    } catch (Exception e) {
      throw new BadRequestException("warehouse.order.create.error");
    }
  }

  public List<WarehouseOrderResponseDto> getOrders(String clientPhone) {
    try {
      return warehouseClient.getOrders(getHeaders(), clientPhone);
    } catch (Exception e) {
      throw new BadRequestException("warehouse.order.get.error");
    }
  }

  public WarehouseOrderResponseDto getOrderById(Long orderId) {
    try {
      return warehouseClient.getOrderById(getHeaders(), orderId);
    } catch (Exception e) {
      throw new BadRequestException("warehouse.order.get.error");
    }
  }

}
