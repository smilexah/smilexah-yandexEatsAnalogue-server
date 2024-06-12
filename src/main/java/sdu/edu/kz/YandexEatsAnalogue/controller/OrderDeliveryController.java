package sdu.edu.kz.YandexEatsAnalogue.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdu.edu.kz.YandexEatsAnalogue.dto.OrderDeliveryDTO;
import sdu.edu.kz.YandexEatsAnalogue.service.OrderDeliveryService;
import sdu.edu.kz.YandexEatsAnalogue.utils.mapper.ModelMapperUtil;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/deliveries")
@RequiredArgsConstructor
public class OrderDeliveryController {
    private final OrderDeliveryService orderDeliveryService;
    private final ModelMapperUtil modelMapperUtil;

    @GetMapping
    public ResponseEntity<?> getAllOrderDeliveries() {
        return new ResponseEntity<>(orderDeliveryService.findAllOrderDeliveries().stream()
                .map(order -> modelMapperUtil.map(order, OrderDeliveryDTO.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{deliveryId}")
    public ResponseEntity<OrderDeliveryDTO> getOrderDeliveryById(@PathVariable Long deliveryId) {
        return orderDeliveryService.findOrderDeliveryById(deliveryId)
                .map(order -> new ResponseEntity<>(modelMapperUtil.map(order, OrderDeliveryDTO.class),
                        HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<OrderDeliveryDTO> createOrderDelivery(@RequestBody OrderDeliveryDTO orderDeliveryDTO) {
        orderDeliveryService.saveOrderDelivery(modelMapperUtil.map(orderDeliveryDTO, OrderDeliveryDTO.class));
        return new ResponseEntity<>(orderDeliveryDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{deliveryId}")
    public ResponseEntity<OrderDeliveryDTO> updateOrderDelivery(@PathVariable Long deliveryId,
            @RequestBody OrderDeliveryDTO orderDeliveryDTO) {
        orderDeliveryService.updateOrderDelivery(modelMapperUtil.map(orderDeliveryDTO, OrderDeliveryDTO.class),
                deliveryId);
        return new ResponseEntity<>(orderDeliveryDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{deliveryId}")
    public ResponseEntity<Void> deleteOrderDelivery(@PathVariable Long deliveryId) {
        orderDeliveryService.deleteOrderDelivery(deliveryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}