package com.qiroldev.monomarket.external.warehouse.dto;
import com.qiroldev.monomarket.external.warehouse.enums.PaymentMethod;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderPaymentResponseDto {

  private Long id;

  private Double cost;

  private PaymentMethod paymentMethod;

  private LocalDateTime dateCreated;

}
