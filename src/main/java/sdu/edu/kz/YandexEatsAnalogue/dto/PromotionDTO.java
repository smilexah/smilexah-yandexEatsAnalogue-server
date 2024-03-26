package sdu.edu.kz.YandexEatsAnalogue.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PromotionDTO {
    private Long promotionId;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal discountPercent;
    private Boolean isActive;
}
