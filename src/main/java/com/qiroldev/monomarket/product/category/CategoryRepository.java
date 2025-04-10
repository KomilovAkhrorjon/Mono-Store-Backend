package com.qiroldev.monomarket.product.category;

import com.qiroldev.monomarket.product.category.dto.CategoryDto;
import com.qiroldev.monomarket.product.system.enums.Lang;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

  @Query("""
      SELECT new com.qiroldev.monomarket.product.category.dto.CategoryDto(
        c.id,
        title.title,
        c.icon,
        c.parent.id
        )
        FROM CategoryEntity c left join c.titles title
        where c.parent.id = :parentId and title.lang = :lang
      """)
  List<CategoryDto> oneLevelCategories(Long parentId, Lang lang);

  @Query("""
          select c
          from CategoryEntity c
          left join c.titles title
          where c.parent.id = :parentId
          order by c.priority desc
          """)
  List<CategoryEntity> oneLevelCategories(Long parentId);

  @Query("""
      SELECT new com.qiroldev.monomarket.product.category.dto.CategoryDto(
        c.id,
        title.title,
        c.icon,
        c.parent.id
        )
        FROM CategoryEntity c left join c.titles title
        where c.id = :id and title.lang = :lang order by c.priority desc
      """)
  Optional<CategoryDto> findByIdAndLang(Long id, Lang lang);

  @Query("""
      SELECT new com.qiroldev.monomarket.product.category.dto.CategoryDto(
        c.id,
        title.title,
        c.icon,
        c.parent.id
        )
        FROM CategoryEntity c left join c.titles title
        where title.lang = :lang order by c.priority desc
      """)
  List<CategoryDto> findAllCustom(Lang lang);

  @Query("""
            select c
            from CategoryEntity c
            left join c.parent c2
            left join c2.parent c3
            left join c3.parent c4
            left join c4.parent c5
            left join c5.parent c6
            where
                 (c.id = :#{#mainCategoryId})
              or (c2 is not null and c2.id = :#{#mainCategoryId})
              or (c3 is null and c3.id = :#{#mainCategoryId})
              or (c4 is null and c4.id = :#{#mainCategoryId})
              or (c5 is null and c5.id = :#{#mainCategoryId})
              or (c6 is null and c6.id = :#{#mainCategoryId})
          """)
  List<CategoryEntity> getChildTree(Long mainCategoryId);

  @Query("""
            select c
            from CategoryEntity c
            left join c.children c2
            left join c2.children c3
            left join c3.children c4
            left join c4.children c5
            left join c5.children c6
            where
                 c.id = :#{#mainCategoryId}
              or (c2 is not null and c2.id = :#{#mainCategoryId})
              or (c3 is not null and c3.id = :#{#mainCategoryId})
              or (c4 is not null and c4.id = :#{#mainCategoryId})
              or (c5 is not null and c5.id = :#{#mainCategoryId})
              or (c6 is not null and c6.id = :#{#mainCategoryId})
          """)
  List<CategoryEntity> getParentTree(Long mainCategoryId);

  @Query("""
            select c
            from CategoryEntity c
            where c.parent is null
          """)
  List<CategoryEntity> findTree();
}
