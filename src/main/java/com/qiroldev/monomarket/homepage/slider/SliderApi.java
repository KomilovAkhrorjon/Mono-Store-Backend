package com.qiroldev.monomarket.homepage.slider;

import com.qiroldev.monomarket.external.filestorage.dto.FileUploadResponseDto;
import com.qiroldev.monomarket.homepage.slider.dto.SliderFullResponseDto;
import com.qiroldev.monomarket.homepage.slider.dto.SliderRequestDto;
import com.qiroldev.monomarket.homepage.slider.dto.SliderResponseDto;
import com.qiroldev.monomarket.homepage.slider.filter.SliderFilterParams;
import jakarta.validation.Valid;
import java.security.Principal;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/v0/slider")
public interface SliderApi {

  @PostMapping()
  ResponseEntity<SliderFullResponseDto> createSlider(
      @RequestBody @Valid SliderRequestDto request, Principal principal
  );

  @PutMapping()
  ResponseEntity<SliderFullResponseDto> updateSlider(
      @RequestBody @Valid SliderRequestDto request, Principal principal
  );

  @GetMapping()
  ResponseEntity<List<SliderResponseDto>> getActiveSliders(SliderFilterParams filterParams);

  @GetMapping("/full")
  ResponseEntity<List<SliderFullResponseDto>> getSliderForManagement(Pageable pageable);

  @PostMapping(
      value = "/upload",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE
  )
  ResponseEntity<List<FileUploadResponseDto>> uploadSliderImage(
      @RequestPart MultipartFile file
  );
}
