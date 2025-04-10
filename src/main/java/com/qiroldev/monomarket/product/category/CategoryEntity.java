package com.qiroldev.monomarket.product.category;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.qiroldev.monomarket.product.category.title.CategoryTitleModel;
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
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "categories")
@RequiredArgsConstructor
public class CategoryEntity {

  private static final String GENERATOR_NAME = "categories_gen";
  private static final String SEQUENCE_NAME = "categories_seq";

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR_NAME)
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  private Long id;

  @OneToMany(mappedBy = "category")
  private List<CategoryTitleModel> titles;

  @Column(name = "is_public")
  private Boolean isPublic;

  @Column(name = "icon")
  private String icon;

  @Column(name = "priority")
  private Integer priority;

  @ManyToOne()
  @JoinColumn(name = "parent_id")
  @JsonBackReference(value = "category-parent")
  private CategoryEntity parent;

  @OneToMany(mappedBy = "parent")
  private List<CategoryEntity> children;

  public CategoryEntity(
      Boolean isPublic,
      String icon,
      Integer priority
  ) {
    this.isPublic = isPublic;
    this.icon = icon;
    this.priority = priority;
  }
}
