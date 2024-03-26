package sdu.edu.kz.YandexEatsAnalogue.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDeliveryDTO {
    private Long deliveryId;
    private Long orderId;
    private Long partnerId;
    private LocalDateTime pickupTime;
    private LocalDateTime deliveryTime;
    private String status;
}
