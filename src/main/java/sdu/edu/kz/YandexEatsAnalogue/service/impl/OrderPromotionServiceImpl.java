package sdu.edu.kz.YandexEatsAnalogue.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdu.edu.kz.YandexEatsAnalogue.entity.OrderPromotion;
import sdu.edu.kz.YandexEatsAnalogue.repository.OrderPromotionRepository;
import sdu.edu.kz.YandexEatsAnalogue.service.OrderPromotionService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderPromotionServiceImpl implements OrderPromotionService {
    @Autowired
    private final OrderPromotionRepository orderPromotionRepository;

    @Override
    public List<OrderPromotion> findAllOrderPromotions() {
        return orderPromotionRepository.findAll();
    }

    @Override
    public Optional<OrderPromotion> findOrderPromotionById(Long id) {
        return orderPromotionRepository.findById(id);
    }

    @Override
    public OrderPromotion saveOrderPromotion(OrderPromotion orderPromotion) {
        return orderPromotionRepository.save(orderPromotion);
    }

    @Override
    public void deleteOrderPromotion(Long id) {
        orderPromotionRepository.deleteById(id);
    }
}
