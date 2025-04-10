package com.qiroldev.monomarket.product.feature.dto;

import com.qiroldev.monomarket.product.feature.title.dto.FeatureTitleDto;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Data;

@Data
public class CreateFeatureRequestDto {

  @NotNull
  private List<FeatureTitleDto> titles;

}
