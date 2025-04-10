package com.qiroldev.monomarket.external.filestorage.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadResponseDto {

  String url;
  String realName;
  String savedName;
  LocalDateTime savedDate;
  String size;
  String fileType;
}
