package com.qiroldev.monomarket.accounting.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findByUsername(String username);

  Optional<UserEntity> findByEmail(String email);

  boolean existsByPhone(String username);

  Optional<UserEntity> findByPhone(String phone);

  boolean existsByUsername(String username);
}
