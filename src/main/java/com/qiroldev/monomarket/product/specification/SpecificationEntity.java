package com.qiroldev.monomarket.product.specification;

import com.qiroldev.monomarket.product.specifficationcategory.SpecificationCategoryEntity;
import com.qiroldev.monomarket.product.specification.title.SpecificationTitleEntity;
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
@Table(name = "specifications")
@RequiredArgsConstructor
public class SpecificationEntity {

  private static final String GENERATOR_NAME = "specifications_gen";
  private static final String SEQUENCE_NAME = "specifications_seq";

  @Id
  @GeneratedValue(generator = GENERATOR_NAME, strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  private Long id;

  @OneToMany(mappedBy = "specification")
  private List<SpecificationTitleEntity> titles;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private SpecificationCategoryEntity category;

  @Column(name = "created_at")
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private LocalDateTime updatedAt;

  public SpecificationEntity(SpecificationCategoryEntity category) {
    this.category = category;
  }

}
