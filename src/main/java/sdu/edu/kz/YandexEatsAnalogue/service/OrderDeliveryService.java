package sdu.edu.kz.YandexEatsAnalogue.service;

import org.springframework.stereotype.Service;
import sdu.edu.kz.YandexEatsAnalogue.entity.OrderDelivery;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderDeliveryService {
    List<OrderDelivery> findAllOrderDeliveries();
    Optional<OrderDelivery> findOrderDeliveryById(Long id);
    OrderDelivery saveOrderDelivery(OrderDelivery orderDelivery);
    void deleteOrderDelivery(Long id);
}
