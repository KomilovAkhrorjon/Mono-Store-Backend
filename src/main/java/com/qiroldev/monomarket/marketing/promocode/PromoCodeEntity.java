package com.qiroldev.monomarket.marketing.promocode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@Entity
@Table(name = "promo_codes")
@RequiredArgsConstructor
public class PromoCodeEntity {

  private static final String GENERATOR_NAME = "promo_codes_gen";
  private static final String SEQUENCE_NAME = "promo_codes_seq";

  @Id
  @GeneratedValue(generator = GENERATOR_NAME, strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  private Long id;

  @Column(name = "code", unique = true)
  private String code;

  @Column(name = "discount")
  private Double discount;

  @Column(name = "is_active")
  private Boolean isActive;

  @Column(name = "created_at")
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private LocalDateTime updatedAt;

  @Column(name = "expired_at")
  private LocalDateTime expiredAt;

  @Column(name = "max_usage")
  private Integer maxUsage;

  @Column(name = "used_count")
  private Integer usedCount;

}
