package sdu.edu.kz.YandexEatsAnalogue.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sdu.edu.kz.YandexEatsAnalogue.dto.OrderDTO;
import sdu.edu.kz.YandexEatsAnalogue.entity.Customer;
import sdu.edu.kz.YandexEatsAnalogue.entity.Order;
import sdu.edu.kz.YandexEatsAnalogue.entity.Restaurant;
import sdu.edu.kz.YandexEatsAnalogue.repository.OrderRepository;
import sdu.edu.kz.YandexEatsAnalogue.service.CustomerService;
import sdu.edu.kz.YandexEatsAnalogue.service.OrderService;
import sdu.edu.kz.YandexEatsAnalogue.service.RestaurantService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final RestaurantService restaurantService;

//    private final RestaurantRepository restaurantRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public void saveOrder(OrderDTO orderDTO) {
        Order order = new Order();

        order.setOrderId(order.getOrderId());

        Customer customer = customerService.findCustomerById(orderDTO.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        order.setCustomer(customer);

        Restaurant restaurant = restaurantService.findRestaurantById(orderDTO.getRestaurantId())
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));
        order.setRestaurant(restaurant);

        order.setOrderTime(orderDTO.getOrderTime());
        order.setDeliveryAddress(orderDTO.getDeliveryAddress());
        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setStatus(orderDTO.getStatus());
        order.setDeliveryMethod(orderDTO.getDeliveryMethod());
        order.setDeliveryInstructions(orderDTO.getDeliveryInstructions());
        order.setDeliveryTimeActual(orderDTO.getDeliveryTimeActual());

        orderRepository.save(order);
    }

    @Override
    public void updateOrder(OrderDTO orderDTO, Long orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isEmpty()) {
            throw new RuntimeException("Order not found");
        }
        Order order = orderOptional.get();

        Customer customer = customerService.findCustomerById(orderDTO.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        order.setCustomer(customer);

        Restaurant restaurant = restaurantService.findRestaurantById(orderDTO.getRestaurantId())
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));
        order.setRestaurant(restaurant);

        order.setOrderTime(orderDTO.getOrderTime());
        order.setDeliveryAddress(orderDTO.getDeliveryAddress());
        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setStatus(orderDTO.getStatus());
        order.setDeliveryMethod(orderDTO.getDeliveryMethod());
        order.setDeliveryInstructions(orderDTO.getDeliveryInstructions());
        order.setDeliveryTimeActual(orderDTO.getDeliveryTimeActual());

        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

//        restaurantRepository.delete(order.getRestaurant());

//        customerService.deleteCustomer(order.getCustomer().getCustomerId());
//        restaurantService.deleteRestaurant(order.getRestaurant().getId());

        orderRepository.deleteById(id);
    }
}
