package com.qiroldev.monomarket.cart;

import com.qiroldev.monomarket.accounting.user.UserService;
import com.qiroldev.monomarket.cart.dto.AddToCartRequestDto;
import com.qiroldev.monomarket.cart.dto.CartResponseDto;
import com.qiroldev.monomarket.cart.dto.CartSkuResponseDto;
import com.qiroldev.monomarket.common.media.MediaMapper;
import com.qiroldev.monomarket.external.warehouse.WarehouseComponent;
import com.qiroldev.monomarket.external.warehouse.dto.OrderProductRequestDto;
import com.qiroldev.monomarket.external.warehouse.dto.WarehouseOrderRequestDto;
import com.qiroldev.monomarket.product.product.ProductService;
import com.qiroldev.monomarket.product.product.sku.SkuService;
import com.qiroldev.monomarket.product.product.sku.skuspecifications.SkuSpecificationsEntity;
import com.qiroldev.monomarket.product.specification.SpecificationEntity;
import com.qiroldev.monomarket.product.specification.SpecificationService;
import com.qiroldev.monomarket.utils.Utils;
import com.qiroldev.monomarket.utils.exception.ResourceNotFoundException;
import com.qiroldev.monomarket.utils.message.Message;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

  private final CartRepository cartRepository;
  private final UserService userService;
  private final SkuService skuService;
  private final SpecificationService specificationService;
  private final MediaMapper mediaMapper;
  private final ProductService productService;
  private final WarehouseComponent warehouseComponent;
  private final Utils utils;

  @Override
  public void addToUser(
      List<AddToCartRequestDto> skuList,
      Principal principal
  ) {

    var user = userService.getUser(principal);

    List<CartEntity> cartList = new ArrayList<>();

    for (var sku : skuList) {

      if (sku.getQuantity() == 0) {
        var mustBeEmptyCart =
            cartRepository.findByUserIdAndSkuIdAndDeletedAtNull(
                user.getId(),
                sku.getSkuId()
            ).orElse(new CartEntity());
        mustBeEmptyCart.setDeletedAt(LocalDateTime.now());
        cartList.add(mustBeEmptyCart);
        continue;
      }

      var cart =
          cartRepository.findByUserIdAndSkuIdAndDeletedAtNull(
              user.getId(),
              sku.getSkuId()
          ).orElse(new CartEntity());
      cart.setUser(user);
      cart.setQuantity(sku.getQuantity());

      if (cart.getSku() == null) {
        var newSku = skuService.findById(sku.getSkuId());
        cart.setSku(newSku);
      }

      cartList.add(cart);
    }

    cartRepository.saveAll(cartList);
  }

  @Override
  public List<CartEntity> getAllByPrincipal(Principal principal) {
    var user = userService.getUser(principal);
    return cartRepository.findAllByUserIdAndDeletedAtNull(user.getId());
  }

  @Override
  public List<CartResponseDto> getAll(Principal principal) {
    var user = userService.getUser(principal);
    var cart = cartRepository.findAllByUserIdAndDeletedAtNull(user.getId());

    List<Long> specificationsList = new ArrayList<>();

    cart.forEach(cartEntity -> {
      var sku = cartEntity.getSku();
      if (sku != null) {
        specificationsList.addAll(
            sku.getSpecifications().stream().map(SkuSpecificationsEntity::getSpecificationId)
                .toList());
      }
    });

    var specifications = specificationService.getByIds(specificationsList)
        .stream().collect(Collectors.toMap(SpecificationEntity::getId, s -> s));

    var skuPrices = warehouseComponent.getSkutPrices(
        cart.stream().map(cartEntity -> cartEntity.getSku().getWarehouseId()).toList()
    );

    return cart.stream().map(
        cartEntity -> {
          var cardSpecifications = cartEntity
              .getSku()
              .getSpecifications()
              .stream()
              .map(
                  skuCharacteristicsEntity -> specifications.get(
                      skuCharacteristicsEntity.getSpecificationId())
              ).toList();

          var mediaList = mediaMapper.toDtoList(
              cartEntity.getSku().getProduct().getMediaList()
          );

          var price = skuPrices.get(cartEntity.getSku().getWarehouseId());

          var sku = new CartSkuResponseDto(
              cartEntity.getSku().getId(),
              price.getSku(),
              price,
              cartEntity.getQuantity(),
              specificationService.toDtoFromEntity(cardSpecifications)
          );

          return new CartResponseDto(
              cartEntity.getId(),
              productService.getProductName(cartEntity.getSku().getProduct().getTitles()),
              mediaList,
              sku
          );
        }
    ).toList();
  }

  @Override
  public void deleteAllByPrincipal(Principal principal) {
    var user = userService.getUser(principal);
    var cartList = cartRepository.findAllByUserIdAndDeletedAtNull(user.getId());
    cartList.forEach(cartEntity -> cartEntity.setDeletedAt(LocalDateTime.now()));
    cartRepository.saveAll(cartList);
  }

  @Override
  public void delete(Long id, Principal principal) {
    var user = userService.getUser(principal);
    var cart = cartRepository.findByIdAndUserIdAndDeletedAtIsNull(id, user.getId())
        .orElseThrow(() -> new ResourceNotFoundException(
            utils.getMessage(Message.CART_PRODUCT_NOT_FOUND),
            List.of(id.toString())));
    cart.setDeletedAt(LocalDateTime.now());
    cartRepository.save(cart);
  }

  @Override
  public void createOrder(Principal principal) {
    var user = userService.getUser(principal);
    var cartList = cartRepository.findAllByUserIdAndDeletedAtNull(user.getId());

    if (cartList.isEmpty()) {
      throw new ResourceNotFoundException(
          utils.getMessage(Message.CART_EMPTY),
          List.of()
      );
    }

    var orderProducts = cartList.stream()
        .map(cart -> new OrderProductRequestDto(
            Long.valueOf(cart.getQuantity()),
            cart.getSku().getWarehouseId()
        )).toList();

    warehouseComponent.createOrder(
        new WarehouseOrderRequestDto(
            user.getPhone(),
            user.getFirstName() + " " + user.getLastName(),
            orderProducts
        )
    );

    cartList.forEach(cartEntity -> cartEntity.setDeletedAt(LocalDateTime.now()));
    cartRepository.saveAll(cartList);
  }
}
