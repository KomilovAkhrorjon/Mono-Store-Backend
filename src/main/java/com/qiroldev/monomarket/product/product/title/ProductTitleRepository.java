package com.qiroldev.monomarket.product.product.title;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductTitleRepository extends JpaRepository<ProductTitleModel, Long> {

  @Query("select p.id from ProductTitleModel p where p.id in :titleIdList")
  List<Long> getExitingTitleIdList(List<Long> titleIdList);

  void deleteAllByProduct_Id(Long categoryId);

}
