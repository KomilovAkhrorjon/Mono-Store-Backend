package com.qiroldev.monomarket.product.brand;

import com.qiroldev.monomarket.accounting.user.UserService;
import com.qiroldev.monomarket.product.brand.dto.BrandDto;
import com.qiroldev.monomarket.product.brand.dto.BrandResponseDto;
import com.qiroldev.monomarket.utils.Utils;
import com.qiroldev.monomarket.utils.exception.BadRequestException;
import com.qiroldev.monomarket.utils.message.Message;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

  private final Utils utils;
  private final BrandRepository brandRepository;
  private final UserService userService;
  private final BrandMapper brandMapper;


  @Override
  public void create(BrandDto request, Principal principal) {

    var user = userService.getUserByUsername(principal.getName());

    var brand = new BrandEntity(
        request.getTitle(),
        request.getLogo(),
        request.getTitleLink(),
        user
    );

    try {
      brandRepository.save(brand);
    } catch (Exception e) {
      throw new BadRequestException(utils.getMessage(Message.DUBLICATION_FOUNDED));
    }
  }

  @Override
  public void update(BrandDto request, Principal principal) {

    var brand = brandRepository.findById(request.getId()).orElse(null);

    if (brand == null) {
      throw new BadRequestException(utils.getMessage(Message.NOT_FOUND_EXCEPTION));
    }

    brand.setTitle(request.getTitle());
    brand.setLogo(request.getLogo());
    brand.setTitleLink(request.getTitleLink());

    try {
      brandRepository.save(brand);
    } catch (Exception e) {
      throw new BadRequestException(utils.getMessage(Message.DUBLICATION_FOUNDED));
    }
  }

  @Override
  public Page<BrandResponseDto> get(Pageable pageable) {
    return brandRepository.findAllAndDto(pageable);
  }

  @Override
  public Page<BrandResponseDto> find(String title, Pageable pageable) {
    return brandRepository.findAllByTitle(title, pageable);
  }

  @Override
  public BrandEntity getBrandById(Long brandId) {
    return brandRepository.findById(brandId).orElse(null);
  }

  @Override
  public BrandResponseDto getDto(BrandEntity brand) {
    return brandMapper.responseDto(brand);
  }


}
