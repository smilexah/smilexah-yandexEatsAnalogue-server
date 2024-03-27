package sdu.edu.kz.YandexEatsAnalogue.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    private Long orderId;
    private Long menuItemId;
    private Integer quantity;
    private BigDecimal priceAtOrderTime;
}
