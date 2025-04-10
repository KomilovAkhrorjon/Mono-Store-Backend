package com.qiroldev.monomarket.common.media;

import com.qiroldev.monomarket.product.product.media.enums.MediaSize;
import com.qiroldev.monomarket.product.product.media.enums.MediaType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaRequestDto {

  private Long id;

  private String fileType;

  private String url;

  private MediaType type;

  private String realName;

  private String savedName;

  private MediaSize size;

}
