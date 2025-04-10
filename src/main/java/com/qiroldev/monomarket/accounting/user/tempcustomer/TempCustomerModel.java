package com.qiroldev.monomarket.accounting.user.tempcustomer;

import com.qiroldev.monomarket.accounting.user.CustomerConfigs;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "temp_consumers")
public class TempCustomerModel {

  private static final String GENERATOR_NAME = "temp_users_gen";

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR_NAME)
  Long id;

  @Column(name = "phone", length = 12, nullable = false)
  String phone;

  @Column(name = "sms_code", length = CustomerConfigs.SMS_CODE_LENGTH,
      nullable = false)
  String smsCode;

  @Column(name = "created_at", nullable = false)
  LocalDateTime createdAt;
}
