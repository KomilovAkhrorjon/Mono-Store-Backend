package com.qiroldev.monomarket.accounting.seller;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<SellerModel, Long> {

  Optional<SellerModel> findByUserUsername(String username);

}
