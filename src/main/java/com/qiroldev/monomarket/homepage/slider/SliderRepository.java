package com.qiroldev.monomarket.homepage.slider;

import com.qiroldev.monomarket.homepage.slider.filter.SliderFilterParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SliderRepository extends JpaRepository<SliderEntity, Long> {

  @Query("""
      select s from SliderEntity s
      where (:#{#filterParams.place} is null or s.place = :#{#filterParams.place})
      and (:#{#filterParams.isActive} is null or s.isActive = :#{#filterParams.isActive})
      """)
  Page<SliderEntity> findAllByFilterParams(SliderFilterParams filterParams, Pageable pageable);
}
