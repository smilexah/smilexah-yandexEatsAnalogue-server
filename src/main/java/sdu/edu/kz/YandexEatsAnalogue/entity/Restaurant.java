package sdu.edu.kz.YandexEatsAnalogue.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
@NoArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RestaurantRating> ratings = new ArrayList<>();

    @Column(nullable = false, length = 255)
    private String address;

    @Column(nullable = false, precision = 2, scale = 1)
    private BigDecimal rating;

    @Column(nullable = false)
    private Integer deliveryTimeEstimate;

    @Column(nullable = false)
    private Boolean isActive;
}