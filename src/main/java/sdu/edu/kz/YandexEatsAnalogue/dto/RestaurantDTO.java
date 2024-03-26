package sdu.edu.kz.YandexEatsAnalogue.dto;

import lombok.*;

import java.math.BigDecimal;

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
