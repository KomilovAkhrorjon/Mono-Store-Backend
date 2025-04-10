package com.qiroldev.monomarket.product.giftcategory;

import com.qiroldev.monomarket.accounting.user.enums.UserRoles;
import com.qiroldev.monomarket.accounting.user.UserService;
import com.qiroldev.monomarket.product.giftcategory.dto.GiftCategoryFilterParams;
import com.qiroldev.monomarket.product.giftcategory.dto.GiftCategoryFullResponseDto;
import com.qiroldev.monomarket.product.giftcategory.dto.GiftCategoryRequestDto;
import com.qiroldev.monomarket.product.giftcategory.dto.GiftCategoryResponseDto;
import com.qiroldev.monomarket.product.giftcategory.dto.GiftCategoryTitleDto;
import com.qiroldev.monomarket.product.giftcategory.title.GiftCategoryTitleModel;
import com.qiroldev.monomarket.product.giftcategory.title.GiftCategoryTitleRepository;
import com.qiroldev.monomarket.product.gifttype.GiftTypeService;
import com.qiroldev.monomarket.utils.Utils;
import com.qiroldev.monomarket.utils.exception.BadRequestException;
import com.qiroldev.monomarket.utils.exception.ResourceNotFoundException;
import com.qiroldev.monomarket.utils.message.Message;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GiftCategoryServiceImpl implements GiftCategoryService {

  private final GiftCategoryRepository giftCategoryRepository;
  private final GiftCategoryTitleRepository giftCategoryTitleRepository;
  private final GiftTypeService giftTypeService;
  private final Utils utils;
  private final UserService userService;
  private final GiftCategoryMapper mapper;

  @Override
  public void create(GiftCategoryRequestDto request, Principal principal) {

    var giftType = giftTypeService.getById(request.getGiftTypeId());

    if (giftType == null) {
      throw new BadRequestException(utils.getMessage(Message.NOT_FOUND_EXCEPTION));
    }

    var user = userService.getUserByUsername(principal.getName());

    var giftCategory = new GiftCategoryEntity(
        true,
        user,
        giftType,
        request.getLink(),
        request.getOrderNo()
    );

    try {
      var savedGiftCategory = giftCategoryRepository.save(giftCategory);

      if (request.getTitles() != null && !request.getTitles().isEmpty()) {
        saveGiftCategoryTitle(savedGiftCategory, request.getTitles());
      }
    } catch (Exception e) {
      throw new BadRequestException(utils.getMessage(Message.SOMETHING_WENT_WRONG));
    }

  }

  @Override
  public void update(GiftCategoryRequestDto request, Principal principal) {

    var giftCategory = giftCategoryRepository.findById(request.getId()).orElse(null);

    if (giftCategory == null) {
      throw new BadRequestException(utils.getMessage(Message.NOT_FOUND_EXCEPTION));
    }

    var user = userService.getUserByUsername(principal.getName());

//    if (!Objects.equals(giftCategory.getUser().getId(), user.getId()) || !user.getRole().equals(
//        UserRoles.ADMIN)) {
//      throw new BadRequestException(utils.getMessage(Message.ACCESS_DENIED));
//    }

    var giftType = giftTypeService.getById(request.getGiftTypeId());

    if (giftType == null) {
      throw new BadRequestException(utils.getMessage(Message.NOT_FOUND_EXCEPTION));
    }

    giftCategory.setIsActive(request.getIsActive());
    giftCategory.setGiftType(giftType);
    giftCategory.setUpdatedBy(user);
    giftCategory.setLink(request.getLink());
    giftCategory.setOrderNo(request.getOrderNo());

    try {

      var savedGiftCategory = giftCategoryRepository.save(giftCategory);

      if (request.getTitles() != null && !request.getTitles().isEmpty()) {
        updateGiftCategoryTitle(savedGiftCategory, request.getTitles());
      }

    } catch (Exception e) {
      throw new BadRequestException(utils.getMessage(Message.SOMETHING_WENT_WRONG));
    }
  }

  @Override
  public void delete(Long id, Principal principal) {

    var giftCategory = giftCategoryRepository.findById(id).orElse(null);

    if (giftCategory == null) {
      throw new BadRequestException(utils.getMessage(Message.NOT_FOUND_EXCEPTION));
    }

    var user = userService.getUserByUsername(principal.getName());

    if (!Objects.equals(giftCategory.getUser().getId(), user.getId()) || !user.getRole().equals(
        UserRoles.ADMIN)) {
      throw new BadRequestException(utils.getMessage(Message.ACCESS_DENIED));
    }

    giftCategory.setIsActive(false);
    giftCategory.setDeletedAt(LocalDateTime.now());
    giftCategory.setDeletedBy(user);

    try {
      giftCategoryRepository.save(giftCategory);
    } catch (Exception e) {
      throw new BadRequestException(utils.getMessage(Message.SOMETHING_WENT_WRONG));
    }
  }

  @Override
  public List<GiftCategoryResponseDto> getAll(GiftCategoryFilterParams filterParams) {
    return giftCategoryRepository.findAllByDeletedAtIsNullAndFilterPrams(
        filterParams
    ).stream().map(this::toDto).toList();
  }

  @Override
  public List<GiftCategoryFullResponseDto> getFullResponse(GiftCategoryFilterParams filterParams) {
    return giftCategoryRepository.getAllByFilterParams(filterParams)
        .stream()
        .map(mapper::toFullResponse)
        .toList();
  }

  @Override
  public GiftCategoryEntity getById(Long giftCategoryId) {
    return giftCategoryRepository.findById(giftCategoryId).orElseThrow(
        () -> new ResourceNotFoundException(
            giftCategoryId.toString(),
            utils.getMessage(Message.NOT_FOUND_EXCEPTION)
        )
    );
  }

  @Override
  public List<GiftCategoryEntity> findAllByType(String giftType) {
    return giftCategoryRepository.findAllByGiftTypeTypeIgnoreCaseAndIsActiveIsTrue(giftType);
  }


  @Override
  public GiftCategoryResponseDto toDto(GiftCategoryEntity model) {
    var title = model.getTitles().stream().filter(
        t -> t.getLang() == utils.getLang()).findFirst().orElse(new GiftCategoryTitleModel());
    return new GiftCategoryResponseDto(
        model.getId(),
        title.getTitle(),
        title.getPhoto(),
        title.getBanner(),
        giftTypeService.toDto(model.getGiftType()),
        model.getLink(),
        model.getOrderNo()
    );
  }

  private void saveGiftCategoryTitle(
      GiftCategoryEntity giftCategory,
      List<GiftCategoryTitleDto> titles) {

    var titlesList = new ArrayList<GiftCategoryTitleModel>();

    for (var title : titles) {

      var titleModel = new GiftCategoryTitleModel();
      titleModel.setTitle(title.getTitle());
      titleModel.setPhoto(title.getPhoto());
      titleModel.setBanner(title.getBanner());
      titleModel.setLang(title.getLang());
      titleModel.setGiftCategory(giftCategory);

      titlesList.add(titleModel);
    }

    giftCategoryTitleRepository.saveAll(titlesList);
  }

  private void updateGiftCategoryTitle(
      GiftCategoryEntity giftCategory,
      List<GiftCategoryTitleDto> titles) {

    var titlesList = new ArrayList<GiftCategoryTitleModel>();

    for (var title : titles) {

      try {
        var titleModel = giftCategoryTitleRepository
            .findById(title.getId())
            .orElse(null);
        if (titleModel == null) {
          continue;
        }
        titleModel.setTitle(title.getTitle());
        titleModel.setPhoto(title.getPhoto());
        titleModel.setBanner(title.getBanner());
        titleModel.setLang(title.getLang());
        titleModel.setGiftCategory(giftCategory);

        titlesList.add(titleModel);
      } catch (Exception e) {
        titlesList.add(new
            GiftCategoryTitleModel(
            title.getTitle(),
            title.getPhoto(),
            title.getBanner(),
            title.getLang(),
            giftCategory
        ));
        log.error("Error while updating gift category title", e);
      }
    }

    giftCategoryTitleRepository.saveAll(titlesList);
  }
}
