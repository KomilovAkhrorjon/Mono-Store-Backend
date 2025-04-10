package com.qiroldev.monomarket.product.product.attribute;

import com.qiroldev.monomarket.product.product.ProductEntity;
import com.qiroldev.monomarket.product.product.attribute.dto.AttributeDto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttributeServiceImpl implements AttributeService {

  private final AttributeRepository attributeRepository;

  @Override
  public void saveList(List<AttributeDto> attributes, ProductEntity product) {



    List<AttributeDto> attributesToSave = new ArrayList<>();
    if (product.getAttributes() != null){
      var attributesToDelete = product.getAttributes().stream().filter(
          attributeEntity -> attributes.stream().noneMatch(
              attributeDto -> attributeDto.getDescription().equals(attributeEntity.getDescription())
          )
      );

      attributeRepository.deleteAll(attributesToDelete.toList());

      attributes.stream().filter(
          attributeDto -> product.getAttributes().stream().noneMatch(
              attributeEntity -> attributeDto.getDescription()
                  .equals(attributeEntity.getDescription())
          )
      ).forEach(attributesToSave::add);
    }
    List<AttributeEntity> attributeEntities = attributesToSave
        .stream()
        .map(dto -> attributeDtoToModel(dto, product))
        .toList();

    attributeRepository.saveAll(attributeEntities);
  }

  private AttributeEntity attributeDtoToModel(AttributeDto dto, ProductEntity product) {
    return new AttributeEntity(
        dto.getDescription(),
        dto.getLang(),
        LocalDateTime.now(),
        product
    );
  }

}
