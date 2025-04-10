package com.qiroldev.monomarket.product.product.media;

import com.qiroldev.monomarket.product.product.ProductEntity;
import com.qiroldev.monomarket.product.product.media.dto.ProductMediaCreateDto;
import java.util.List;

public interface MediaService {

  List<MediaEntity> create(
      ProductEntity productEntity,
      List<ProductMediaCreateDto> requestList
  );
}
