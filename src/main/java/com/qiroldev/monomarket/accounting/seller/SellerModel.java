package com.qiroldev.monomarket.accounting.seller;

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
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "sellers")
@RequiredArgsConstructor
public class SellerModel {

  private static final String GENERATOR_NAME = "sellers_gen";
  private static final String SEQUENCE_NAME = "sellers_seq";

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR_NAME)
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  private Long id;

  @Column(name = "title", nullable = false, unique = true)
  private String title;

  @Column(name = "description_uz")
  private String descriptionUz;

  @Column(name = "description_ru")
  private String descriptionRu;

  @Column(name = "description_en")
  private String descriptionEn;

  @Column(name = "logo")
  private String logo;

  @Column(name = "banner")
  private String banner;

  @Column(name = "warehouse_id")
  private Long warehouseId;

  @Column(name = "phone", length = 12)
  private String phone;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  @Column(name = "created_at", nullable = false)
  LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  UserEntity user;

  public SellerModel(
      String title,
      UserEntity user,
      String password,
      LocalDateTime createdAt) {
    this.title = title;
    this.user = user;
    this.password = password;
    this.createdAt = createdAt;
  }
}
