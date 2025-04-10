package com.qiroldev.monomarket.cart;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {

  @Query("""
      select c
      from CartEntity c
      where c.user.id = :#{#userId}
      and c.deletedAt is null
      order by c.createdAt desc
      """)
  List<CartEntity> findAllByUserIdAndDeletedAtNull(Long userId);

  Optional<CartEntity> findByUserIdAndSkuIdAndDeletedAtNull(Long userId, Long skuId);

  Optional<CartEntity> findByIdAndUserIdAndDeletedAtIsNull(Long id, Long userId);
}
