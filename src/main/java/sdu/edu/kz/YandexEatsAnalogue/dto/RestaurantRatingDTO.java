package sdu.edu.kz.YandexEatsAnalogue.dto;

import lombok.*;
import sdu.edu.kz.YandexEatsAnalogue.entity.Customer;
import sdu.edu.kz.YandexEatsAnalogue.entity.Restaurant;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantRatingDTO {
    private Long restaurantId;
    private Long customerId;
    private BigDecimal rating;
    private String comment;
    private LocalDateTime createdAt;
}
