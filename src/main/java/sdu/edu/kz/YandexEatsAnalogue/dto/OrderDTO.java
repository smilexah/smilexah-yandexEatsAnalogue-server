package sdu.edu.kz.YandexEatsAnalogue.dto;

import lombok.*;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long customerId;
    private Long restaurantId;
    private LocalDateTime orderTime;
    private String deliveryAddress;
    private BigDecimal totalPrice;
    private String status;
    private String deliveryMethod;
    private String deliveryInstructions;
    private Integer deliveryTimeActual;
    private Set<OrderItemDTO> orderItems;
    private Set<OrderPromotionDTO> orderPromotions;
    private Set<OrderDeliveryDTO> orderDeliveries;
}
