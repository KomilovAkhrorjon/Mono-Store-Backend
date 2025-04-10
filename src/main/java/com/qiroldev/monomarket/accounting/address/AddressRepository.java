package com.qiroldev.monomarket.accounting.address;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

  List<AddressEntity> findAllByUserId(Long userId);

}
