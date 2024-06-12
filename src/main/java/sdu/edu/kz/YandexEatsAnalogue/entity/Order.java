package sdu.edu.kz.YandexEatsAnalogue.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Account customer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    private LocalDateTime orderTime;

    private String deliveryAddress;

    private BigDecimal totalPrice;

    private String status;

    private String deliveryMethod;

    private String deliveryInstructions;

    private Integer deliveryTimeActual;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<OrderItem> orderItems = new HashSet<>();

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<OrderPromotion> orderPromotions = new HashSet<>();

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<OrderDelivery> orderDeliveries = new HashSet<>();

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<PaymentDetail> paymentDetails;
}
