package sdu.edu.kz.YandexEatsAnalogue.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_promotions")
@Getter
@Setter
@NoArgsConstructor
public class OrderPromotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderPromotionId;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "order_id", referencedColumnName = "orderId", nullable = true)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "promotion_id", referencedColumnName = "promotionId", nullable = true)
    private Promotion promotion;

}
