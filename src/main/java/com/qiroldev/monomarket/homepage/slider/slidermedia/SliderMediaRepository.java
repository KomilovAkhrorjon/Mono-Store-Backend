package com.qiroldev.monomarket.homepage.slider.slidermedia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SliderMediaRepository extends JpaRepository<SliderMediaEntity, Long> {

}
