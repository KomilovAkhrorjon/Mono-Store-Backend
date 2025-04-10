package com.qiroldev.monomarket.product.brand;

import com.qiroldev.monomarket.accounting.user.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@Entity
@Table(name = "brands")
@RequiredArgsConstructor
public class BrandEntity {

  private static final String GENERATOR_NAME = "brands_gen";
  private static final String SEQUENCE_NAME = "brands_seq";

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR_NAME)
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  private Long id;

  @Column(name = "title", unique = true)
  private String title;

  @Column(name = "logo", nullable = false)
  private String logo;

  @Column(name = "title_link")
  private String titleLink;

  @ManyToOne()
  @JoinColumn(name = "created_by")
  private UserEntity createdBy;

  @Column(name = "created_at")
  @CreationTimestamp()
  private LocalDateTime createdAt;

  public BrandEntity(
      String title,
      String logo,
      String titleLink,
      UserEntity user) {
    this.title = title;
    this.logo = logo;
    this.titleLink = titleLink;
    this.createdBy = user;
  }
}
