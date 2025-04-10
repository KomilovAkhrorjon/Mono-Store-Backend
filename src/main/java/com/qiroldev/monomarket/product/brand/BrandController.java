package com.qiroldev.monomarket.product.brand;

import com.qiroldev.monomarket.external.filestorage.FileStorageComponent;
import com.qiroldev.monomarket.external.filestorage.dto.FileUploadResponseDto;
import com.qiroldev.monomarket.external.filestorage.enums.FolderType;
import com.qiroldev.monomarket.product.brand.dto.BrandDto;
import com.qiroldev.monomarket.product.brand.dto.BrandResponseDto;
import jakarta.validation.Valid;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class BrandController implements BrandApi {

  private final BrandService service;
  private final FileStorageComponent fileStorageComponent;


  @Override
  public ResponseEntity<Void> create(
      @RequestBody @Valid BrandDto request,
      Principal principal) {
    service.create(request, principal);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  public ResponseEntity<Void> edit(
      @RequestBody @Valid BrandDto request,
      Principal principal) {

    service.update(request, principal);

    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  @Override
  public ResponseEntity<Page<BrandResponseDto>> get(Pageable pageable) {
    return ResponseEntity.ok(service.get(pageable));
  }

  @Override
  public ResponseEntity<Page<BrandResponseDto>> get(@RequestParam String title, Pageable pageable) {
    return ResponseEntity.ok(service.find(title, pageable));
  }

  @Override
  public ResponseEntity<FileUploadResponseDto> uploadLogo(MultipartFile file) {
    return ResponseEntity.ok(fileStorageComponent.uploadSingle(file, FolderType.BRAND));
  }

}
