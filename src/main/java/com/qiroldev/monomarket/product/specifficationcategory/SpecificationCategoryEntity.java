package com.qiroldev.monomarket.product.specifficationcategory;

import com.qiroldev.monomarket.accounting.seller.SellerModel;
import com.qiroldev.monomarket.product.specifficationcategory.title.SpecificationCategoryTitleEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@Entity
@Table(name = "specification_categories")
@RequiredArgsConstructor
public class SpecificationCategoryEntity {

  private static final String GENERATOR_NAME = "specification_categories_gen";
  private static final String SEQUENCE_NAME = "specification_categories_seq";

  @Id
  @GeneratedValue(generator = GENERATOR_NAME, strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  private Long id;

  @OneToMany(mappedBy = "specificationCategory")
  private List<SpecificationCategoryTitleEntity> titles;

  @Column(name = "is_active")
  private Boolean isActive;

  @Column(name = "deleted_at")
  private LocalDateTime deletedAt;

  @Column(name = "created_at")
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private LocalDateTime updatedAt;

  @ManyToOne
  @JoinColumn(name = "seller_id")
  private SellerModel createdBy;

  public SpecificationCategoryEntity(SellerModel seller, Boolean isActive) {
    this.isActive = isActive;
    this.createdBy = seller;
  }
}
