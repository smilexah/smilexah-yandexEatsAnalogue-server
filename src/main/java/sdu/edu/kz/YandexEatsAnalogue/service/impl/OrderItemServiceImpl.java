package sdu.edu.kz.YandexEatsAnalogue.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdu.edu.kz.YandexEatsAnalogue.entity.OrderItem;
import sdu.edu.kz.YandexEatsAnalogue.repository.OrderItemRepository;
import sdu.edu.kz.YandexEatsAnalogue.service.OrderItemService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
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
    public OrderItem saveOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
    @Override
    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }
}
