package com.qiroldev.monomarket.accounting.user.tempcustomer;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempCustomerRepository extends JpaRepository<TempCustomerModel, Long> {

  Optional<TempCustomerModel> findByPhoneAndSmsCodeAndCreatedAtAfter(
      String phone,
      String smsCode,
      LocalDateTime createdAt);

  int countByPhoneAndCreatedAtIsAfter(String phone, LocalDateTime createdAt);

}
