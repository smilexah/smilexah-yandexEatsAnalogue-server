package sdu.edu.kz.YandexEatsAnalogue.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdu.edu.kz.YandexEatsAnalogue.dto.OrderItemDTO;
import sdu.edu.kz.YandexEatsAnalogue.service.OrderItemService;
import sdu.edu.kz.YandexEatsAnalogue.utils.ModelMapperUtil;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orderItems")
@RequiredArgsConstructor
public class OrderItemController {
    private final OrderItemService orderItemService;
    private final ModelMapperUtil modelMapperUtil;

    @GetMapping
    public ResponseEntity<List<OrderItemDTO>> getAllOrderItems() {
        return new ResponseEntity<>(orderItemService.findAllOrderItems().stream()
                .map(orderItem -> modelMapperUtil.map(orderItem, OrderItemDTO.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{orderItemId}")
    public ResponseEntity<OrderItemDTO> getOrderItemById(@PathVariable Long orderItemId) {
        return orderItemService.findOrderItemById(orderItemId)
                .map(orderItem -> ResponseEntity.ok(modelMapperUtil.map(orderItem, OrderItemDTO.class)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<OrderItemDTO> createOrderItem(@RequestBody OrderItemDTO orderItemDTO) {
        orderItemService.saveOrderItem(modelMapperUtil.map(orderItemDTO, OrderItemDTO.class));
        return new ResponseEntity<>(orderItemDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{orderItemId}")
    public ResponseEntity<OrderItemDTO> updateOrderItem(@PathVariable Long orderItemId,
            @RequestBody OrderItemDTO orderItemDTO) {
        return orderItemService.findOrderItemById(orderItemId)
                .map(orderItem -> {
                    orderItemService.updateOrderItem(orderItemDTO, orderItemId);
                    return new ResponseEntity<>(orderItemDTO, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{orderItemId}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long orderItemId) {
        orderItemService.deleteOrderItem(orderItemId);
        return ResponseEntity.noContent().build();
    }
}
