package com.qiroldev.monomarket.product.giftcategory;

import com.qiroldev.monomarket.external.filestorage.dto.FileUploadResponseDto;
import com.qiroldev.monomarket.product.giftcategory.dto.GiftCategoryFilterParams;
import com.qiroldev.monomarket.product.giftcategory.dto.GiftCategoryFullResponseDto;
import com.qiroldev.monomarket.product.giftcategory.dto.GiftCategoryRequestDto;
import com.qiroldev.monomarket.product.giftcategory.dto.GiftCategoryResponseDto;
import jakarta.validation.Valid;
import java.security.Principal;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/v0/gift-category")
public interface GiftCategoryApi {


  @PostMapping()
  ResponseEntity<Void> create(
      @RequestBody @Valid GiftCategoryRequestDto request,
      Principal principal);

  @PutMapping()
  ResponseEntity<Void> update(
      @RequestBody @Valid GiftCategoryRequestDto request,
      Principal principal);

  @DeleteMapping("/{id}")
  ResponseEntity<Void> delete(@PathVariable Long id, Principal principal);

  @GetMapping()
  ResponseEntity<List<GiftCategoryResponseDto>> get(GiftCategoryFilterParams filterParams);

  @GetMapping("/full")
  ResponseEntity<List<GiftCategoryFullResponseDto>> getFullResponse(
      GiftCategoryFilterParams filterParams);

  @PostMapping(
      value = "/upload",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE
  )
  ResponseEntity<FileUploadResponseDto> uploadFile(
      @RequestPart MultipartFile file
  );
}
