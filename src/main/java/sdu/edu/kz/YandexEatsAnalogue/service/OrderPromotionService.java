package sdu.edu.kz.YandexEatsAnalogue.service;

import org.springframework.stereotype.Service;
import sdu.edu.kz.YandexEatsAnalogue.entity.OrderPromotion;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderPromotionService {
    List<OrderPromotion> findAllOrderPromotions();

    Optional<OrderPromotion> findOrderPromotionById(Long id);

    OrderPromotion saveOrderPromotion(OrderPromotion orderPromotion);

    void deleteOrderPromotion(Long id);
}
