package com.qiroldev.monomarket.accounting.user;

import com.qiroldev.monomarket.accounting.user.dto.MinimumUserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(
    componentModel = ComponentModel.SPRING,
    uses = {
        UserMapper.class
    }
)
public interface UserMapper {

  @Mapping(target = "id", source = "id")
  MinimumUserResponseDto minimumUserResponseDto(UserEntity user);

}
