package sdu.edu.kz.YandexEatsAnalogue.service;

import org.springframework.stereotype.Service;
import sdu.edu.kz.YandexEatsAnalogue.dto.OrderItemDTO;
import sdu.edu.kz.YandexEatsAnalogue.entity.OrderItem;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderItemService {
    List<OrderItem> findAllOrderItems();
    Optional<OrderItem> findOrderItemById(Long id);
    void saveOrderItem(OrderItemDTO orderItemDTO);
    void updateOrderItem(OrderItemDTO orderItemDTO, Long id);
    void deleteOrderItem(Long id);
}
