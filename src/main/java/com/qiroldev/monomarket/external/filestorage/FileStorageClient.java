package com.qiroldev.monomarket.external.filestorage;

import com.qiroldev.monomarket.external.filestorage.dto.FileUploadResponseDto;
import com.qiroldev.monomarket.external.filestorage.enums.FolderType;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "file-storage-service", url = "localhost:6659")
public interface FileStorageClient {

  @PostMapping(
      value = "/v1/document/upload",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  List<FileUploadResponseDto> uploadFile(
      @RequestPart MultipartFile multipartFile,
      @RequestParam FolderType folderType);

  @PostMapping(
      value = "/v1/document/single",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  FileUploadResponseDto uploadSingle(
      @RequestPart MultipartFile multipartFile,
      @RequestParam FolderType folderType
  );
}
