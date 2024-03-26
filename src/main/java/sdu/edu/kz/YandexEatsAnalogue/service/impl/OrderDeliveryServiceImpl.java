package sdu.edu.kz.YandexEatsAnalogue.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdu.edu.kz.YandexEatsAnalogue.entity.OrderDelivery;
import sdu.edu.kz.YandexEatsAnalogue.repository.OrderDeliveryRepository;
import sdu.edu.kz.YandexEatsAnalogue.service.OrderDeliveryService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderDeliveryServiceImpl implements OrderDeliveryService {
    @Autowired
    private OrderDeliveryRepository orderDeliveryRepository;

    public List<OrderDelivery> findAllOrderDeliveries() {
        return orderDeliveryRepository.findAll();
    }

    public Optional<OrderDelivery> findOrderDeliveryById(Long id) {
        return orderDeliveryRepository.findById(id);
    }

    public OrderDelivery saveOrderDelivery(OrderDelivery orderDelivery) {
        return orderDeliveryRepository.save(orderDelivery);
    }

    public void deleteOrderDelivery(Long id) {
        orderDeliveryRepository.deleteById(id);
    }
}
