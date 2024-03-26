package sdu.edu.kz.YandexEatsAnalogue.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderPromotionDTO {
    private Long orderPromotionId;
    private Long orderId;
    private Long promotionId;
}
