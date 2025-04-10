package com.qiroldev.monomarket.common.dto;

import com.qiroldev.monomarket.common.media.MediaResponseDto;
import com.qiroldev.monomarket.product.system.enums.Lang;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FullTitleResponseDto {

  private Long id;

  private String title;

  private String description;

  private Lang lang;

  private List<MediaResponseDto> mediaList;

}
