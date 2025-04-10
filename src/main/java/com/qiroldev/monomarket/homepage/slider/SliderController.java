package com.qiroldev.monomarket.homepage.slider;

import com.qiroldev.monomarket.external.filestorage.dto.FileUploadResponseDto;
import com.qiroldev.monomarket.homepage.slider.dto.SliderFullResponseDto;
import com.qiroldev.monomarket.homepage.slider.dto.SliderRequestDto;
import com.qiroldev.monomarket.homepage.slider.dto.SliderResponseDto;
import com.qiroldev.monomarket.homepage.slider.filter.SliderFilterParams;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SliderController implements SliderApi {

  private final SliderService service;

  @Override
  public ResponseEntity<SliderFullResponseDto> createSlider(SliderRequestDto request, Principal principal) {
    return ResponseEntity.ok(service.createSlider(request, principal));
  }

  @Override
  public ResponseEntity<SliderFullResponseDto> updateSlider(SliderRequestDto request, Principal principal) {
    return ResponseEntity.ok(service.updateSlider(request.getId(), request, principal));
  }

  @Override
  public ResponseEntity<List<SliderResponseDto>> getActiveSliders(SliderFilterParams filterParams) {
    return ResponseEntity.ok(service.getActiveSliders(filterParams));
  }

  @Override
  public ResponseEntity<List<SliderFullResponseDto>> getSliderForManagement(
      Pageable pageable) {
    return ResponseEntity.ok(service.getSliderForManagement(pageable));
  }

  @Override
  public ResponseEntity<List<FileUploadResponseDto>> uploadSliderImage(MultipartFile file) {
    return ResponseEntity.ok(service.uploadSliderImage(file));
  }
}
