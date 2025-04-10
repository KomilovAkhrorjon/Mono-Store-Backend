package com.qiroldev.monomarket.order;


import com.qiroldev.monomarket.accounting.address.AddressService;
import com.qiroldev.monomarket.accounting.user.UserService;
import com.qiroldev.monomarket.cart.CartService;
import com.qiroldev.monomarket.cart.dto.CartResponseDto;
import com.qiroldev.monomarket.cart.dto.CartSkuResponseDto;
import com.qiroldev.monomarket.common.media.MediaMapper;
import com.qiroldev.monomarket.external.warehouse.WarehouseComponent;
import com.qiroldev.monomarket.external.warehouse.dto.SkuPriceResponseDto;
import com.qiroldev.monomarket.marketing.promocode.PromoCodeService;
import com.qiroldev.monomarket.order.dto.CreateOrderRequestDto;
import com.qiroldev.monomarket.order.dto.OrderResponseDto;
import com.qiroldev.monomarket.order.ordersku.OrderSkuEntity;
import com.qiroldev.monomarket.order.ordersku.OrderSkuRepository;
import com.qiroldev.monomarket.product.product.ProductService;
import com.qiroldev.monomarket.product.product.sku.SkuEntity;
import com.qiroldev.monomarket.product.product.sku.SkuService;
import com.qiroldev.monomarket.product.product.sku.skuspecifications.SkuSpecificationsEntity;
import com.qiroldev.monomarket.product.specification.SpecificationEntity;
import com.qiroldev.monomarket.product.specification.SpecificationMapper;
import com.qiroldev.monomarket.product.specification.SpecificationService;
import com.qiroldev.monomarket.utils.exception.ResourceNotFoundException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final OrderSkuRepository orderSkuRepository;
  private final CartService cartService;
  private final AddressService addressService;
  private final PromoCodeService promoCodeService;
  private final UserService userService;
  private final WarehouseComponent warehouseComponent;
  private final SkuService skuService;
  private final ProductService productService;
  private final MediaMapper mediaMapper;
  private final SpecificationMapper specificationMapper;
  private final SpecificationService specificationService;

  @Override
  public void create(CreateOrderRequestDto requestDt, Principal principal) {

    var cart = cartService.getAllByPrincipal(principal);
    var address = addressService.findById(requestDt.getAddressId());
    var user = userService.getUser(principal);

    var order = new OrderEntity();

    var totalSum = cart.stream()
        .mapToDouble(cartEntity ->
            warehouseComponent.getSkutPrice(cartEntity.getSku().getId()).getDiscountPrice()
                * cartEntity.getQuantity()
        )
        .sum();

    var promoCodeResult = promoCodeService.calculateByCode(requestDt.getUsedPromoCodes(), totalSum);
    order.setTotalDiscount(promoCodeResult.getDiscount());
    order.setUser(user);
    order.setUsedPromoCodes(requestDt.getUsedPromoCodes());
    order.setAddress(address);
    order.setDiscountPercentage(promoCodeResult.getTotalPercentage());

    var savedOrder = orderRepository.save(order);

    List<OrderSkuEntity> orderSkus = new ArrayList<>();

    for (var cartEntity : cart) {
      orderSkus.add(new OrderSkuEntity(
          savedOrder,
          cartEntity.getSku(),
          cartEntity.getQuantity()
      ));
    }

    orderSkuRepository.saveAll(orderSkus);

    cartService.deleteAllByPrincipal(principal);

  }

  @Override
  public List<OrderResponseDto> getOrders(Principal principal) {

    var user = userService.getUser(principal);

    var warehouseResponse = warehouseComponent.getOrders(user.getPhone());

    return warehouseResponse.stream().map(
        orderResponse -> new OrderResponseDto(
            orderResponse.getId(),
            orderResponse.getPayment().getCost(),
            orderResponse.getOrderProducts().size(),
            orderResponse.getOrderStatus(),
            orderResponse.getCreatedAt()
        )
    ).toList();
  }

  @Override
  public List<CartResponseDto> getOrderProducts(Long orderId, Principal principal) {

    var user = userService.getUser(principal);

    var warehouseOrder = warehouseComponent.getOrderById(orderId);

    var orderClientResult = warehouseOrder.getClient().getClientPhones()
        .stream()
        .anyMatch(phone -> Objects.equals(phone.getPhoneNumber(), user.getPhone()));

    if (!orderClientResult) {
      throw new ResourceNotFoundException("order_not_found", List.of(orderId.toString()));
    }

    var warehouseSkuIdList = warehouseOrder.getOrderProducts()
        .stream()
        .map(orderProduct -> orderProduct.getProductOption().getId())
        .toList();

    var skuList = skuService.findAllByWarehouseIdList(warehouseSkuIdList);

    var usedSpecifications = specificationService.getByIds(
            skuList.stream()
                .flatMap(sku -> sku.getSpecifications().stream())
                .map(SkuSpecificationsEntity::getSpecificationId)
                .toList())
        .stream()
        .collect(Collectors.toMap(SpecificationEntity::getId, Function.identity()));

    var skuMap = skuList.stream()
        .collect(
            Collectors.toMap(SkuEntity::getWarehouseId, Function.identity(),
                (existing, replacement) -> existing)
        );

    return warehouseOrder.getOrderProducts()
        .stream()
        .map(orderProduct -> {
          var sku = skuMap.get(orderProduct.getProductOption().getId());

          if (sku == null) {
            throw new ResourceNotFoundException("sku_not_found",
                List.of(orderProduct.getProductOption().getSku()));
          }

          var product = sku.getProduct();

          var skuSpecifications = sku.getSpecifications().stream().map(
                  skuSpecificationsEntity -> usedSpecifications.get(
                      skuSpecificationsEntity.getSpecificationId()))
              .toList();

          var specsDto = specificationService.toDtoFromEntity(skuSpecifications);

          return new CartResponseDto(
              product.getId(),
              productService.getProductName(product.getTitles()),
              mediaMapper.toDtoList(product.getMediaList()),
              new CartSkuResponseDto(
                  sku.getId(),
                  orderProduct.getProductOption().getSku(),
                  new SkuPriceResponseDto(
                      sku.getId(),
                      orderProduct.getProductOption().getMarketPrice(),
                      orderProduct.getProductOption().getMarketSellingPrice(),
                      orderProduct.getProductOption().getAvailableAmount(),
                      orderProduct.getProductOption().getSku()
                  ),
                  orderProduct.getAmount(),
                  specsDto
              )
          );
        })
        .toList();

  }

}
