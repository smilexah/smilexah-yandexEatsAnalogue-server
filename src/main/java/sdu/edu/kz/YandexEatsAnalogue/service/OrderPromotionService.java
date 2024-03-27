package sdu.edu.kz.YandexEatsAnalogue.service;

import org.springframework.stereotype.Service;
import sdu.edu.kz.YandexEatsAnalogue.dto.OrderPromotionDTO;
import sdu.edu.kz.YandexEatsAnalogue.entity.OrderPromotion;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderPromotionService {
    List<OrderPromotion> findAllOrderPromotions();

    Optional<OrderPromotion> findOrderPromotionById(Long id);

    void saveOrderPromotion(OrderPromotionDTO orderPromotionDTO);

    void updateOrderPromotion(OrderPromotionDTO orderPromotionDTO, Long id);

    void deleteOrderPromotion(Long id);
}
