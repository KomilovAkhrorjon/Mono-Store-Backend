package com.qiroldev.monomarket.product.giftcategory;

import com.qiroldev.monomarket.product.giftcategory.dto.GiftCategoryFilterParams;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftCategoryRepository extends JpaRepository<GiftCategoryEntity, Long> {

  @Query("""
          select gc
          from GiftCategoryEntity gc
          where gc.deletedAt is null
            and (:#{#filterParams.giftTypeId} is null or gc.giftType.id = :#{#filterParams.giftTypeId})
      """)
  List<GiftCategoryEntity> findAllByDeletedAtIsNullAndFilterPrams(
      GiftCategoryFilterParams filterParams);

  @Query("""
      select gc
      from GiftCategoryEntity gc
      where (:#{#filterParams.giftTypeId} is null or gc.giftType.id = :#{#filterParams.giftTypeId})
      """)
  List<GiftCategoryEntity> getAllByFilterParams(GiftCategoryFilterParams filterParams);

  List<GiftCategoryEntity> findAllByGiftTypeTypeIgnoreCaseAndIsActiveIsTrue(String giftType);

}
