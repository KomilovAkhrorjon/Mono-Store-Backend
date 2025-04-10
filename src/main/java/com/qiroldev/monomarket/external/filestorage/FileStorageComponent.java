package com.qiroldev.monomarket.external.filestorage;

import com.qiroldev.monomarket.external.filestorage.dto.FileUploadResponseDto;
import com.qiroldev.monomarket.external.filestorage.enums.FolderType;
import com.qiroldev.monomarket.utils.exception.BadRequestException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Slf4j
@RequiredArgsConstructor
public class FileStorageComponent {

  private final FileStorageClient fileStorageClient;

  public List<FileUploadResponseDto> uploadFile(MultipartFile file, FolderType folderType) {
    try {
      return fileStorageClient.uploadFile(file, folderType);
    } catch (Exception e) {
      log.error("Error uploading file: {}", e.getMessage());
      throw new BadRequestException("file.upload.error");
    }
  }

  public FileUploadResponseDto uploadSingle(MultipartFile file, FolderType folderType) {
    try {
      return fileStorageClient.uploadSingle(file, folderType);
    } catch (Exception e) {
      log.error("Error uploading file: {}", e.getMessage());
      throw new BadRequestException("file.upload.error");
    }
  }
}
