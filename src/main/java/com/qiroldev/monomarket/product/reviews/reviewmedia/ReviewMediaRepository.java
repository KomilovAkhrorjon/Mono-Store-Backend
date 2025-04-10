package com.qiroldev.monomarket.product.reviews.reviewmedia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewMediaRepository extends JpaRepository<ReviewMediaEntity, Long> {

}
