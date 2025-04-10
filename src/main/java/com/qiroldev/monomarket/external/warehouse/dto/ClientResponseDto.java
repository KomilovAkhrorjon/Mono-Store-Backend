package com.qiroldev.monomarket.external.warehouse.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientResponseDto {

  private Long id;

  private String name;

  private String note;

  private String address;

  private List<ClientPhoneResponseDto> clientPhones;

}
