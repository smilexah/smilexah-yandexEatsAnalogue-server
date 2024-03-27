package sdu.edu.kz.YandexEatsAnalogue.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_delivery")
@Getter
@Setter
@NoArgsConstructor
public class OrderDelivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "partner_id", nullable = false)
    private DeliveryPartner deliveryPartner;

    private LocalDateTime pickupTime;

    private LocalDateTime deliveryTime;

    private String status;
}
