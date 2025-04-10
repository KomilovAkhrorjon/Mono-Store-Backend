package com.qiroldev.monomarket.product.gifttype;

import com.qiroldev.monomarket.product.gifttype.dto.GiftTypeDto;
import com.qiroldev.monomarket.product.gifttype.dto.GiftTypeFullResponseDto;
import com.qiroldev.monomarket.product.gifttype.dto.GiftTypeResponseDto;
import com.qiroldev.monomarket.product.gifttype.dto.GiftTypeTitleDto;
import com.qiroldev.monomarket.product.gifttype.title.GiftTypeTitleModel;
import com.qiroldev.monomarket.product.gifttype.title.GiftTypeTitleRepository;
import com.qiroldev.monomarket.utils.Utils;
import com.qiroldev.monomarket.utils.exception.BadRequestException;
import com.qiroldev.monomarket.utils.message.Message;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GiftTypeServiceImpl implements GiftTypeService {

  private final GiftTypeRepository giftTypeRepository;
  private final GiftTypeTitleRepository giftTypeTitleRepository;
  private final Utils utils;
  private final GiftTypeMapper mapper;

  @Override
  public void create(GiftTypeDto request) {

    var giftType = new GiftTypeEntity(
        request.getType(),
        request.getIsActive()
    );

    try {
      var savedGiftType = giftTypeRepository.save(giftType);
      saveGiftTypeTitle(savedGiftType, request.getTitles());
    } catch (Exception e) {
      throw new BadRequestException(utils.getMessage(Message.SOMETHING_WENT_WRONG));
    }
  }

  @Override
  public void update(GiftTypeDto request) {

    var giftType = giftTypeRepository.findById(request.getId()).orElse(null);
    if (giftType == null) {
      throw new BadRequestException(utils.getMessage(Message.NOT_FOUND_EXCEPTION));
    }

    giftType.setIsActive(request.getIsActive());
    giftType.setType(request.getType());

    try {
      var savedGiftCategory = giftTypeRepository.save(giftType);
      updateGiftTypeTitle(savedGiftCategory, request.getTitles());
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new BadRequestException(utils.getMessage(Message.SOMETHING_WENT_WRONG));
    }

  }

  @Override
  public List<GiftTypeResponseDto> getAll() {
    return giftTypeRepository
        .findAllByIsActiveIsTrue().stream().map(this::toDto).toList();
  }

  @Override
  public GiftTypeEntity getById(Long id) {
    return giftTypeRepository.findById(id).orElse(null);
  }

  @Override
  public List<GiftTypeFullResponseDto> getFullData() {
    return giftTypeRepository.findAll().stream().map(mapper::toFullResponse).toList();
  }

  @Override
  public GiftTypeResponseDto toDto(GiftTypeEntity giftType) {
    var title = giftType.getTitles().stream().filter(
        t -> t.getLang() == utils.getLang()).findFirst().orElse(new GiftTypeTitleModel()
    );
    return new GiftTypeResponseDto(
        giftType.getId(),
        giftType.getType(),
        title.getTitle(),
        title.getSubtitle()
    );
  }

  private void saveGiftTypeTitle(GiftTypeEntity giftType, List<GiftTypeTitleDto> titles) {

    var giftTypeTitlesList = new ArrayList<GiftTypeTitleModel>();

    titles.forEach(title -> {
      var giftTypeTitle = new GiftTypeTitleModel(
          title.getTitle(),
          title.getSubtitle(),
          title.getLang(),
          giftType
      );
      giftTypeTitlesList.add(giftTypeTitle);
    });

    giftTypeTitleRepository.saveAll(giftTypeTitlesList);
  }

  private void updateGiftTypeTitle(GiftTypeEntity giftType, List<GiftTypeTitleDto> titles) {

    var giftTypeTitlesList = new ArrayList<GiftTypeTitleModel>();

    titles.forEach(title -> {
      try {
        var giftTypeTitle = giftTypeTitleRepository
            .findById(title.getId())
            .orElse(null);
        if (giftTypeTitle == null) {
          return;
        }
        giftTypeTitle.setTitle(title.getTitle());
        giftTypeTitle.setSubtitle(title.getSubtitle());
        giftTypeTitle.setLang(title.getLang());
        giftTypeTitle.setGiftType(giftType);
        giftTypeTitlesList.add(giftTypeTitle);
      } catch (Exception e) {
        giftTypeTitlesList.add(new GiftTypeTitleModel(
            title.getTitle(),
            title.getSubtitle(),
            title.getLang(),
            giftType
        ));
      }
    });

    giftTypeTitleRepository.saveAll(giftTypeTitlesList);
  }


}
