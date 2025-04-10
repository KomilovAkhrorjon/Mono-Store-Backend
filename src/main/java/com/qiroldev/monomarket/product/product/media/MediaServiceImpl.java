package com.qiroldev.monomarket.product.product.media;

import com.qiroldev.monomarket.product.product.ProductEntity;
import com.qiroldev.monomarket.product.product.media.dto.ProductMediaCreateDto;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {

  private final MediaRepository mediaRepository;

  @Override
  public List<MediaEntity> create(
      ProductEntity productEntity,
      List<ProductMediaCreateDto> requestList
  ) {

    List<MediaEntity> mediaListToSave = new ArrayList<>();

    if (productEntity.getMediaList() != null){
      var mediaListToDelete = productEntity.getMediaList().stream()
          .filter(mediaEntity -> requestList.stream()
              .noneMatch(request -> request.getSavedName().equals(mediaEntity.getSavedName())))
          .toList();

      mediaRepository.deleteAll(mediaListToDelete);

      var mediaListToUpdate = productEntity.getMediaList().stream()
          .filter(mediaEntity -> requestList.stream()
              .anyMatch(request -> request.getSavedName().equals(mediaEntity.getSavedName())))
          .toList();

      mediaListToUpdate.forEach(mediaEntity -> {
        var request = requestList.stream()
            .filter(requestDto -> requestDto.getSavedName().equals(mediaEntity.getSavedName()))
            .findFirst().orElseThrow();
        mediaEntity.setOrderNumber(request.getOrderNumber());
        mediaListToSave.add(mediaEntity);
      });

      mediaListToSave.addAll(requestList.stream()
          .filter(request -> productEntity.getMediaList().stream()
              .noneMatch(mediaEntity -> mediaEntity.getSavedName().equals(request.getSavedName())))
          .map(request -> toEntity(request, productEntity))
          .toList());

    }else {
      mediaListToSave.addAll(requestList.stream()
          .map(request -> toEntity(request, productEntity))
          .toList());
    }

    return mediaRepository.saveAll(mediaListToSave);
  }

  private MediaEntity toEntity(ProductMediaCreateDto request, ProductEntity productEntity) {
    return new MediaEntity(
        request.getMediaKey(),
        request.getUrl(),
        request.getType(),
        request.getName(),
        request.getSavedName(),
        request.getSize(),
        request.getOrderNumber(),
        productEntity
    );
  }

}
