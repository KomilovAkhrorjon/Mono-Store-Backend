package com.qiroldev.monomarket.cart.wishlist;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<WishlistEntity, Long> {

  Integer countAllByUserUsername(String username);

  List<WishlistEntity> findAllByUserUsername(String username);

}
