package sdu.edu.kz.YandexEatsAnalogue.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import sdu.edu.kz.YandexEatsAnalogue.dto.OrderPromotionDTO;
import sdu.edu.kz.YandexEatsAnalogue.entity.Order;
import sdu.edu.kz.YandexEatsAnalogue.entity.OrderPromotion;
import sdu.edu.kz.YandexEatsAnalogue.entity.Promotion;
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
    public void saveOrderPromotion(OrderPromotionDTO orderPromotionDTO) {
        OrderPromotion orderPromotion = new OrderPromotion();

        orderPromotion.setOrderPromotionId(orderPromotionDTO.getOrderPromotionId());

        Order order = new Order();
        order.setOrderId(orderPromotionDTO.getOrderId());
        orderPromotion.setOrder(order);

        Promotion promotion = new Promotion();
        promotion.setPromotionId(orderPromotionDTO.getPromotionId());
        orderPromotion.setPromotion(promotion);

        orderPromotionRepository.save(orderPromotion);
    }

    @Override
    public void updateOrderPromotion(OrderPromotionDTO orderPromotionDTO, Long id) {
        Optional<OrderPromotion> orderPromotionOptional = orderPromotionRepository.findById(id);
        if (orderPromotionOptional.isEmpty()) {
            throw new EntityNotFoundException("Order promotion not found");
        }
        OrderPromotion orderPromotion = orderPromotionOptional.get();

        Order order = new Order();
        order.setOrderId(orderPromotionDTO.getOrderId());
        orderPromotion.setOrder(order);

        Promotion promotion = new Promotion();
        promotion.setPromotionId(orderPromotionDTO.getPromotionId());
        orderPromotion.setPromotion(promotion);

        orderPromotionRepository.save(orderPromotion);
    }

    @Override
    public void deleteOrderPromotion(Long id) {
        orderPromotionRepository.deleteById(id);
    }
}
