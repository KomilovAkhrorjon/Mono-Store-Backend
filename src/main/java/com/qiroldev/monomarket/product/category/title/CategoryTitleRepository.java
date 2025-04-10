package com.qiroldev.monomarket.product.category.title;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryTitleRepository extends JpaRepository<CategoryTitleModel, Long> {

  @Query("select c.id from CategoryTitleModel c where c.id in :titleIdList")
  List<Long> getExitingTitleIdList(List<Long> titleIdList);

  void deleteAllByCategory_Id(Long categoryId);

}
