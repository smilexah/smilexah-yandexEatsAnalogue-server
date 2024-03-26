package sdu.edu.kz.YandexEatsAnalogue.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemDTO {
    private Long menuItemId;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean isActive;
}
