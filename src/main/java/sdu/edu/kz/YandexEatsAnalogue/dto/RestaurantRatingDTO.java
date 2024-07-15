package sdu.edu.kz.YandexEatsAnalogue.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantRatingDTO {
    private Long restaurantId;
    private Long customerId;
    private BigDecimal rating;
    private String comment;
    private LocalDateTime createdAt;
}
