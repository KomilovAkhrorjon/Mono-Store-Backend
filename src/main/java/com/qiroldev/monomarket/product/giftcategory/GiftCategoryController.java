package com.qiroldev.monomarket.product.giftcategory;

import com.qiroldev.monomarket.external.filestorage.FileStorageComponent;
import com.qiroldev.monomarket.external.filestorage.dto.FileUploadResponseDto;
import com.qiroldev.monomarket.external.filestorage.enums.FolderType;
import com.qiroldev.monomarket.product.giftcategory.dto.GiftCategoryFilterParams;
import com.qiroldev.monomarket.product.giftcategory.dto.GiftCategoryFullResponseDto;
import com.qiroldev.monomarket.product.giftcategory.dto.GiftCategoryRequestDto;
import com.qiroldev.monomarket.product.giftcategory.dto.GiftCategoryResponseDto;
import jakarta.validation.Valid;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class GiftCategoryController implements GiftCategoryApi {

  private final GiftCategoryService giftCategoryService;
  private final FileStorageComponent fileStorageComponent;

  @Override
  public ResponseEntity<Void> create(
      GiftCategoryRequestDto request,
      Principal principal) {
    giftCategoryService.create(request, principal);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  public ResponseEntity<Void> update(
      @RequestBody @Valid GiftCategoryRequestDto request,
      Principal principal) {
    giftCategoryService.update(request, principal);
    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  @Override
  public ResponseEntity<Void> delete(@PathVariable Long id, Principal principal) {
    giftCategoryService.delete(id, principal);
    return ResponseEntity.status(HttpStatus.GONE).build();
  }

  @Override
  public ResponseEntity<List<GiftCategoryResponseDto>> get(GiftCategoryFilterParams filterParams) {
    return ResponseEntity.ok(giftCategoryService.getAll(filterParams));
  }

  @Override
  public ResponseEntity<List<GiftCategoryFullResponseDto>> getFullResponse(
      GiftCategoryFilterParams filterParams) {
    return ResponseEntity.ok(giftCategoryService.getFullResponse(filterParams));
  }

  @Override
  public ResponseEntity<FileUploadResponseDto> uploadFile(MultipartFile file) {
    return ResponseEntity.ok(fileStorageComponent.uploadSingle(file, FolderType.GIFT));
  }

}
