package sdu.edu.kz.YandexEatsAnalogue.service;

import org.springframework.stereotype.Service;
import sdu.edu.kz.YandexEatsAnalogue.dto.OrderDTO;
import sdu.edu.kz.YandexEatsAnalogue.entity.Order;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderService {
    void createOrder(Order order);
    void updateOrder(Order orderDTO);
    void deleteOrder(Long id);
    Order getOrder(Long id);
    List<Order> getAllOrders();
    Optional<Order> getOrderById(Long orderId);
    Order saveOrder(Order order);
    Optional<Order> findOrderById(Long orderId);
}
