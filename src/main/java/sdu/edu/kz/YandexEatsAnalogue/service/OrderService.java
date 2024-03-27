package sdu.edu.kz.YandexEatsAnalogue.service;

import org.springframework.stereotype.Service;
import sdu.edu.kz.YandexEatsAnalogue.dto.OrderDTO;
import sdu.edu.kz.YandexEatsAnalogue.entity.Order;

import java.util.List;
import java.util.Optional;


@Service
public interface OrderService {
    List<Order> getAllOrders();
    Optional<Order> findOrderById(Long orderId);
    void saveOrder(OrderDTO orderDTO);
    void updateOrder(OrderDTO orderDTO, Long id);
    void deleteOrder(Long id);
}
