package com.qiroldev.monomarket.homepage.slider.title;

import com.qiroldev.monomarket.homepage.slider.filter.SliderFilterParams;
import com.qiroldev.monomarket.product.system.enums.Lang;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SliderTitleRepository extends JpaRepository<SliderTitleEntity, Long> {

  @Query("""
            select t from SliderTitleEntity t
            where t.lang = :lang
            and (:#{#filterParams.place} is null or t.slider.place = :#{#filterParams.place})
            and t.slider.isActive = true
            order by t.slider.orderNo asc
      """)
  List<SliderTitleEntity> findAllByLangAndFilterParamsAndActive(Lang lang, SliderFilterParams filterParams);
}
