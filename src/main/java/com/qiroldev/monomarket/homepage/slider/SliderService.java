package com.qiroldev.monomarket.homepage.slider;

import com.qiroldev.monomarket.external.filestorage.dto.FileUploadResponseDto;
import com.qiroldev.monomarket.homepage.slider.dto.SliderFullResponseDto;
import com.qiroldev.monomarket.homepage.slider.dto.SliderRequestDto;
import com.qiroldev.monomarket.homepage.slider.dto.SliderResponseDto;
import com.qiroldev.monomarket.homepage.slider.filter.SliderFilterParams;
import java.security.Principal;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;


public interface SliderService {

  SliderFullResponseDto createSlider(SliderRequestDto request, Principal principal);

  SliderFullResponseDto getSliderById(Long id);

  SliderFullResponseDto updateSlider(Long id, SliderRequestDto request, Principal principal);

  void deleteSlider(Long id);

  List<SliderResponseDto> getActiveSliders(SliderFilterParams filterParams);

  List<SliderFullResponseDto> getSliderForManagement(Pageable pageable);

  List<FileUploadResponseDto> uploadSliderImage(MultipartFile file);
}
