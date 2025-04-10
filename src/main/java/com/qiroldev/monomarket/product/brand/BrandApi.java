package com.qiroldev.monomarket.product.brand;

import com.qiroldev.monomarket.external.filestorage.dto.FileUploadResponseDto;
import com.qiroldev.monomarket.product.brand.dto.BrandDto;
import com.qiroldev.monomarket.product.brand.dto.BrandResponseDto;
import jakarta.validation.Valid;
import java.security.Principal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/v0/brand")
public interface BrandApi {

  @PostMapping()
  ResponseEntity<Void> create(
      @RequestBody @Valid BrandDto request,
      Principal principal);

  @PutMapping()
  ResponseEntity<Void> edit(
      @RequestBody @Valid BrandDto request,
      Principal principal);

  @GetMapping()
  ResponseEntity<Page<BrandResponseDto>> get(Pageable pageable);

  @GetMapping("/search")
  ResponseEntity<Page<BrandResponseDto>> get(@RequestParam String title, Pageable pageable);

  @PostMapping(
      value = "/upload",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE
  )
  ResponseEntity<FileUploadResponseDto> uploadLogo(
      @RequestPart MultipartFile file
  );

}
