package sdu.edu.kz.YandexEatsAnalogue.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdu.edu.kz.YandexEatsAnalogue.dto.OrderDeliveryDTO;
import sdu.edu.kz.YandexEatsAnalogue.entity.OrderDelivery;
import sdu.edu.kz.YandexEatsAnalogue.service.OrderDeliveryService;
import sdu.edu.kz.YandexEatsAnalogue.utils.ModelMapperUtil;

@RestController
@RequestMapping("/orderDeliveries")
@RequiredArgsConstructor
public class OrderDeliveryController {

    @Autowired
    private final OrderDeliveryService orderDeliveryService;

    // Assume CustomMapper contains static methods for converting between entities
    // and DTOs
    @Autowired
    private final ModelMapperUtil modelMapperUtil;

    @GetMapping
    public ResponseEntity<?> getAllOrderDeliveries() {
        return new ResponseEntity<>(orderDeliveryService.findAllOrderDeliveries().stream()
                .map(orderDelivery -> modelMapperUtil.toOrderDeliveryDTO(orderDelivery)).toArray(), HttpStatus.OK);
    }

    @GetMapping("/{deliveryId}")
    public ResponseEntity<OrderDeliveryDTO> getOrderDeliveryById(@PathVariable Long deliveryId) {
        return orderDeliveryService.findOrderDeliveryById(deliveryId)
                .map(orderDelivery -> new ResponseEntity<>(modelMapperUtil.toOrderDeliveryDTO(orderDelivery),
                        HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<OrderDeliveryDTO> createOrderDelivery(@RequestBody OrderDeliveryDTO orderDeliveryDTO) {
        orderDeliveryService.saveOrderDelivery(orderDeliveryDTO);
        return new ResponseEntity<>(orderDeliveryDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{deliveryId}")
    public ResponseEntity<OrderDeliveryDTO> updateOrderDelivery(@PathVariable Long deliveryId,
            @RequestBody OrderDeliveryDTO orderDeliveryDTO) {
        orderDeliveryService.updateOrderDelivery(orderDeliveryDTO, deliveryId);
        return new ResponseEntity<>(orderDeliveryDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{deliveryId}")
    public ResponseEntity<Void> deleteOrderDelivery(@PathVariable Long deliveryId) {
        orderDeliveryService.deleteOrderDelivery(deliveryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}