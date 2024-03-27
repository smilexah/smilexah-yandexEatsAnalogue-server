package sdu.edu.kz.YandexEatsAnalogue.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemDTO {
    private Long restaurantId;
    private String name;
    private Integer weight;
    private Integer calories;
    private String description;
    private BigDecimal price;
    private Boolean isAvailable;
}
