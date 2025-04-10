package com.qiroldev.monomarket.homepage.slider;

import com.qiroldev.monomarket.common.media.MediaMapper;
import com.qiroldev.monomarket.common.media.MediaRequestDto;
import com.qiroldev.monomarket.external.filestorage.FileStorageComponent;
import com.qiroldev.monomarket.external.filestorage.dto.FileUploadResponseDto;
import com.qiroldev.monomarket.external.filestorage.enums.FolderType;
import com.qiroldev.monomarket.homepage.slider.dto.SliderFullResponseDto;
import com.qiroldev.monomarket.homepage.slider.dto.SliderRequestDto;
import com.qiroldev.monomarket.homepage.slider.dto.SliderResponseDto;
import com.qiroldev.monomarket.homepage.slider.filter.SliderFilterParams;
import com.qiroldev.monomarket.homepage.slider.slidermedia.SliderMediaEntity;
import com.qiroldev.monomarket.homepage.slider.slidermedia.SliderMediaRepository;
import com.qiroldev.monomarket.homepage.slider.title.SliderTitleEntity;
import com.qiroldev.monomarket.homepage.slider.title.SliderTitleRepository;
import com.qiroldev.monomarket.utils.Utils;
import com.qiroldev.monomarket.utils.exception.ResourceNotFoundException;
import com.qiroldev.monomarket.utils.message.Message;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class SliderServiceImpl implements SliderService {

  private final SliderRepository sliderRepository;
  private final SliderTitleRepository sliderTitleRepository;
  private final SliderMediaRepository sliderMediaRepository;
  private final Utils utils;
  private final MediaMapper mediaMapper;
  private final SliderMapper sliderMapper;
  private final FileStorageComponent fileStorageComponent;

  @Override
  @Transactional
  public SliderFullResponseDto createSlider(SliderRequestDto request, Principal principal) {

    SliderEntity slider = new SliderEntity();
    slider.setColor(request.getColor());
    slider.setLink(request.getLink());
    slider.setIsActive(request.getIsActive());
    slider.setPlace(request.getPlace());
    slider.setOrderNo(request.getOrderNo());

    var savedSlider = sliderRepository.save(slider);

    saveTitles(request, savedSlider);

    return getSliderById(savedSlider.getId());
  }

  @Override
  public SliderFullResponseDto getSliderById(Long id) {
    var result = sliderRepository.findById(id).orElseThrow(
        () -> new ResourceNotFoundException(
            id.toString(),
            List.of(utils.getMessage(Message.SLIDER_NOT_FOUND))
        )
    );

    return sliderToFullDto(result);
  }

  @Override
  public SliderFullResponseDto updateSlider(Long id, SliderRequestDto request,
      Principal principal) {

    var slider = sliderRepository.findById(id).orElseThrow(
        () -> new ResourceNotFoundException(
            id.toString(),
            List.of(utils.getMessage(Message.SLIDER_NOT_FOUND))
        )
    );

    slider.setColor(request.getColor());
    slider.setLink(request.getLink());
    slider.setIsActive(request.getIsActive());
    slider.setOrderNo(request.getOrderNo());
    slider.setPlace(request.getPlace());

    slider.getTitles()
        .forEach(title -> sliderMediaRepository.deleteAll(title.getMediaList()));

    sliderTitleRepository.deleteAll(slider.getTitles());

    saveTitles(request, slider);

    slider.setTitles(null);

    return sliderToFullDto(sliderRepository.save(slider));
  }

  private void saveTitles(SliderRequestDto request, SliderEntity slider) {
    request.getTitles().forEach(title -> {
      var sliderTitle = new SliderTitleEntity();
      sliderTitle.setTitle(title.getTitle());
      sliderTitle.setLang(title.getLang());
      sliderTitle.setSlider(slider);
      var savedSliderTitle = sliderTitleRepository.save(sliderTitle);

      sliderMediaRepository.saveAll(
          title.getMediaList().stream()
              .map(media -> sliderMediaToEntity(media, savedSliderTitle))
              .toList()
      );
    });
  }

  @Override
  public void deleteSlider(Long id) {
    var slider = sliderRepository.findById(id).orElseThrow(
        () -> new ResourceNotFoundException(
            id.toString(),
            List.of(utils.getMessage(Message.SLIDER_NOT_FOUND))
        )
    );
    sliderRepository.delete(slider);
  }

  @Override
  public List<SliderResponseDto> getActiveSliders(SliderFilterParams filterParams) {
    return sliderTitleRepository.findAllByLangAndFilterParamsAndActive(
            utils.getLang(),
            filterParams
        ).stream()
        .map(this::sliderToDto)
        .toList();
  }

  @Override
  public List<SliderFullResponseDto> getSliderForManagement(Pageable pageable) {
    return sliderRepository.findAll(pageable)
        .stream()
        .map(sliderMapper::toDto)
        .toList();
  }

  @Override
  public List<FileUploadResponseDto> uploadSliderImage(MultipartFile file) {
    return fileStorageComponent.uploadFile(file, FolderType.SLIDER);
  }


  private SliderMediaEntity sliderMediaToEntity(
      MediaRequestDto dto,
      SliderTitleEntity sliderTitle
  ) {
    return new SliderMediaEntity(
        dto.getFileType(),
        dto.getUrl(),
        dto.getType(),
        dto.getRealName(),
        dto.getSavedName(),
        dto.getSize(),
        sliderTitle
    );
  }

  private SliderResponseDto sliderToDto(SliderTitleEntity title) {
    var slider = title.getSlider();
    return new SliderResponseDto(
        slider.getId(),
        title.getTitle(),
        slider.getColor(),
        slider.getLink(),
        title.getMediaList().stream()
            .map(mediaMapper::responseDto)
            .toList()
    );
  }

  private SliderFullResponseDto sliderToFullDto(SliderEntity slider) {
    return sliderMapper.toDto(slider);
  }

}
