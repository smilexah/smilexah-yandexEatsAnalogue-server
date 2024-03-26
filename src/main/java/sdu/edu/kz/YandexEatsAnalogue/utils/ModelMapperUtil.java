package sdu.edu.kz.YandexEatsAnalogue.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;
import sdu.edu.kz.YandexEatsAnalogue.dto.*;
import sdu.edu.kz.YandexEatsAnalogue.entity.*;
import sdu.edu.kz.YandexEatsAnalogue.service.DeliveryPartnerService;
import sdu.edu.kz.YandexEatsAnalogue.service.OrderService;
import sdu.edu.kz.YandexEatsAnalogue.service.PromotionService;

@Component
@RequiredArgsConstructor
public class ModelMapperUtil {
    private final ModelMapper modelMapper = new ModelMapper();

    public <S, T> T map(S source, Class<T> targetClass) {
        return modelMapper.map(source, targetClass);
    }

    public RestaurantDTO mapToRestaurantDTO(Restaurant restaurant) {
        return modelMapper.map(restaurant, RestaurantDTO.class);
    }

    public static DeliveryPartnerDTO toDeliveryPartnerDTO(DeliveryPartner partner) {
        DeliveryPartnerDTO dto = new DeliveryPartnerDTO();
        dto.setPartnerId(partner.getPartnerId());
        dto.setName(partner.getName());
        dto.setPhone(partner.getPhone());
        dto.setIsActive(partner.getIsActive());
        return dto;
    }

    public Restaurant mapToRestaurantEntity(RestaurantDTO dto) {
        return modelMapper.map(dto, Restaurant.class);
    }

    public OrderDeliveryDTO toOrderDeliveryDTO(OrderDelivery orderDelivery) {
        OrderDeliveryDTO dto = new OrderDeliveryDTO();
        dto.setDeliveryId(orderDelivery.getDeliveryId());
        dto.setOrderId(orderDelivery.getOrder().getOrderId());
        dto.setPartnerId(orderDelivery.getDeliveryPartner().getPartnerId());
        dto.setPickupTime(orderDelivery.getPickupTime());
        dto.setDeliveryTime(orderDelivery.getDeliveryTime());
        dto.setStatus(orderDelivery.getStatus());
        return dto;
    }

    private final DeliveryPartnerService deliveryPartnerService;
    private final OrderService orderService;

    public DeliveryPartner convertPartnerIdToEntity(Long partnerId) {
        return deliveryPartnerService.findDeliveryPartnerById(partnerId)
                .orElseThrow(() -> new RuntimeException("DeliveryPartner not found with id: " + partnerId));
    }

    public Order convertOrderIdToEntity(Long orderId) {
        return orderService.findOrderById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
    }

    public PaymentDetailDTO toPaymentDetailDTO(PaymentDetail paymentDetail) {
        PaymentDetailDTO dto = new PaymentDetailDTO();
        dto.setPaymentId(paymentDetail.getPaymentId());
        dto.setOrderId(paymentDetail.getOrder().getOrderId()); // Assuming direct mapping for simplicity
        dto.setAmount(paymentDetail.getAmount());
        dto.setPaymentMethod(paymentDetail.getPaymentMethod());
        dto.setPaymentStatus(paymentDetail.getPaymentStatus());
        dto.setTransactionID(paymentDetail.getTransactionID());
        dto.setPaymentDateTime(paymentDetail.getPaymentDateTime());
        return dto;
    }

    public PromotionDTO toPromotionDTO(Promotion promotion) {
        PromotionDTO dto = new PromotionDTO();
        dto.setPromotionId(promotion.getPromotionId());
        dto.setDescription(promotion.getDescription());
        dto.setStartDate(promotion.getStartDate());
        dto.setEndDate(promotion.getEndDate());
        dto.setDiscountPercent(promotion.getDiscountPercent());
        dto.setIsActive(promotion.getIsActive());
        return dto;
    }

    public Promotion toPromotionEntity(PromotionDTO dto) {
        Promotion promotion = new Promotion();
        promotion.setPromotionId(dto.getPromotionId()); // This should be null for new creations
        promotion.setDescription(dto.getDescription());
        promotion.setStartDate(dto.getStartDate());
        promotion.setEndDate(dto.getEndDate());
        promotion.setDiscountPercent(dto.getDiscountPercent());
        promotion.setIsActive(dto.getIsActive());
        return promotion;
    }

    public void updatePromotionFromDTO(PromotionDTO dto, Promotion promotion) {
        promotion.setDescription(dto.getDescription());
        promotion.setStartDate(dto.getStartDate());
        promotion.setEndDate(dto.getEndDate());
        promotion.setDiscountPercent(dto.getDiscountPercent());
        promotion.setIsActive(dto.getIsActive());
    }

    @Autowired
    private final PromotionService promotionService;

    public OrderPromotionDTO toOrderPromotionDTO(OrderPromotion orderPromotion) {
        OrderPromotionDTO dto = new OrderPromotionDTO();
        dto.setOrderPromotionId(orderPromotion.getOrderPromotionId());
        dto.setOrderId(orderPromotion.getOrder().getOrderId());
        dto.setPromotionId(orderPromotion.getPromotion().getPromotionId());
        return dto;
    }

    public OrderPromotion toOrderPromotionEntity(OrderPromotionDTO dto) {
        OrderPromotion orderPromotion = new OrderPromotion();
        orderPromotion.setOrderPromotionId(dto.getOrderPromotionId()); // This might be null for new creations

        // Fetch and set the Order and Promotion by ID
        Order order = orderService.findOrderById(dto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));
        Promotion promotion = promotionService.findPromotionById(dto.getPromotionId())
                .orElseThrow(() -> new RuntimeException("Promotion not found"));

        orderPromotion.setOrder(order);
        orderPromotion.setPromotion(promotion);
        return orderPromotion;
    }

    public void updateOrderPromotionFromDTO(OrderPromotionDTO dto, OrderPromotion orderPromotion) {
        // Assuming you may want to update the associated Order or Promotion, fetch fresh instances
        Order order = orderService.findOrderById(dto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));
        Promotion promotion = promotionService.findPromotionById(dto.getPromotionId())
                .orElseThrow(() -> new RuntimeException("Promotion not found"));

        orderPromotion.setOrder(order);
        orderPromotion.setPromotion(promotion);
    }
}