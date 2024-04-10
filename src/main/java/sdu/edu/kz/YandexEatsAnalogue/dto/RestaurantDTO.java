package sdu.edu.kz.YandexEatsAnalogue.dto;

import lombok.*;
import sdu.edu.kz.YandexEatsAnalogue.entity.RestaurantRating;

import java.math.BigDecimal;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {
    private Long restaurantId;
    private String name;
    private String address;
    private BigDecimal rating;
    private Integer deliveryTimeEstimate;
    private Boolean isActive;
}
