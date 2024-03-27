package sdu.edu.kz.YandexEatsAnalogue.dto;

import jakarta.persistence.*;
import lombok.*;
import sdu.edu.kz.YandexEatsAnalogue.entity.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetailDTO {
    private Long orderId;
    private BigDecimal amount;
    private String paymentMethod;
    private String paymentStatus;
    private String transactionID;
    private LocalDateTime paymentDateTime;
}
