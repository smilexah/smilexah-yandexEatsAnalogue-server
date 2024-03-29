package sdu.edu.kz.YandexEatsAnalogue.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sdu.edu.kz.YandexEatsAnalogue.dto.OrderItemDTO;
import sdu.edu.kz.YandexEatsAnalogue.entity.MenuItem;
import sdu.edu.kz.YandexEatsAnalogue.entity.Order;
import sdu.edu.kz.YandexEatsAnalogue.entity.OrderItem;
import sdu.edu.kz.YandexEatsAnalogue.repository.OrderItemRepository;
import sdu.edu.kz.YandexEatsAnalogue.service.OrderItemService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItem> findAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @Override
    public Optional<OrderItem> findOrderItemById(Long id) {
        return orderItemRepository.findById(id);
    }

    @Override
    public void saveOrderItem(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = new OrderItem();

        MenuItem menuItem = new MenuItem();
        menuItem.setMenuItemId(orderItemDTO.getMenuItemId());
        orderItem.setMenuItem(menuItem);

        Order order = new Order();
        order.setOrderId(orderItemDTO.getOrderId());
        orderItem.setOrder(order);

        orderItem.setQuantity(orderItemDTO.getQuantity());
        orderItem.setPriceAtOrderTime(orderItemDTO.getPriceAtOrderTime());
        orderItemRepository.save(orderItem);
    }

    @Override
    public void updateOrderItem(OrderItemDTO orderItemDTO, Long id) {
        Optional<OrderItem> orderItemOptional = orderItemRepository.findById(id);
        if (orderItemOptional.isEmpty()) {
            throw new EntityNotFoundException("Order item not found");
        }
        OrderItem orderItem = orderItemOptional.get();

        MenuItem menuItem = new MenuItem();
        menuItem.setMenuItemId(orderItemDTO.getMenuItemId());
        orderItem.setMenuItem(menuItem);

        Order order = new Order();
        order.setOrderId(orderItemDTO.getOrderId());
        orderItem.setOrder(order);

        orderItem.setQuantity(orderItemDTO.getQuantity());
        orderItem.setPriceAtOrderTime(orderItemDTO.getPriceAtOrderTime());
        orderItemRepository.save(orderItem);
    }

    @Override
    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }

}
