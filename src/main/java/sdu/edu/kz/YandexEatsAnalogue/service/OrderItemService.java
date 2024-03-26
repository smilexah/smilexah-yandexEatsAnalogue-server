package sdu.edu.kz.YandexEatsAnalogue.service;

import org.springframework.stereotype.Service;
import sdu.edu.kz.YandexEatsAnalogue.entity.OrderItem;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderItemService {
    List<OrderItem> findAllOrderItems();
    Optional<OrderItem> findOrderItemById(Long id);
    OrderItem saveOrderItem(OrderItem orderItem);
    void deleteOrderItem(Long id);
}
