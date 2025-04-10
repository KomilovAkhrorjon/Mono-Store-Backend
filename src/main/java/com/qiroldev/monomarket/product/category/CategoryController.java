package com.qiroldev.monomarket.product.category;

import com.qiroldev.monomarket.external.filestorage.FileStorageComponent;
import com.qiroldev.monomarket.external.filestorage.dto.FileUploadResponseDto;
import com.qiroldev.monomarket.external.filestorage.enums.FolderType;
import com.qiroldev.monomarket.product.category.dto.CategoryCreateDto;
import com.qiroldev.monomarket.product.category.dto.CategoryFullResponseDto;
import com.qiroldev.monomarket.product.category.dto.CategoryResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v0/category")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService service;
  private final FileStorageComponent fileStorageComponent;

  @PostMapping()
  public ResponseEntity<Void> create(@RequestBody CategoryCreateDto request) {
    service.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PutMapping()
  public ResponseEntity<Void> update(@RequestBody CategoryCreateDto request) {
    service.update(request);

    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<CategoryResponseDto> getOneLevelCategories(@PathVariable Long id) {
    return ResponseEntity.ok(service.getOneLevelCategories(id));
  }

  @GetMapping("/tree")
  public ResponseEntity<List<CategoryResponseDto>> getCategoriesTree() {
    return ResponseEntity.ok(service.getCategoriesTree());
  }

  @GetMapping("/full")
  public ResponseEntity<List<CategoryFullResponseDto>> getCategories() {
    return ResponseEntity.ok(service.getFullCategory());
  }

  @PostMapping( value = "/upload/icon",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<FileUploadResponseDto> uploadCategoryIcon(@RequestPart MultipartFile file) {
    return ResponseEntity.ok(fileStorageComponent.uploadSingle(file, FolderType.PRODUCT));
  }

}
