package sdu.edu.kz.YandexEatsAnalogue.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdu.edu.kz.YandexEatsAnalogue.dto.CustomerDTO;
import sdu.edu.kz.YandexEatsAnalogue.dto.OrderDTO;
import sdu.edu.kz.YandexEatsAnalogue.entity.Order;
import sdu.edu.kz.YandexEatsAnalogue.service.OrderService;
import sdu.edu.kz.YandexEatsAnalogue.utils.ModelMapperUtil;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ModelMapperUtil modelMapperUtil;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders().stream()
                .map(order -> modelMapperUtil.map(order, OrderDTO.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long orderId) {
        return orderService.findOrderById(orderId)
                .map(order -> ResponseEntity.ok(modelMapperUtil.map(order, OrderDTO.class)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        orderService.saveOrder(orderDTO);
        return new ResponseEntity<>(orderDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long orderId, @RequestBody OrderDTO orderDTO) {
        return orderService.findOrderById(orderId)
                .map(order -> {
                    orderService.updateOrder(orderDTO, orderId);
                    return new ResponseEntity<>(orderDTO, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}