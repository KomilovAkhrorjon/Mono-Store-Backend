package com.qiroldev.monomarket.product.questions;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

  @Query("SELECT q FROM QuestionEntity q WHERE q.product.id = :productId AND q.disabled = false")
  Page<QuestionEntity> findAllByProductIdAndDisabledIsFalse(Long productId, Pageable pageable);
}
