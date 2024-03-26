package sdu.edu.kz.YandexEatsAnalogue.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdu.edu.kz.YandexEatsAnalogue.dto.OrderItemDTO;
import sdu.edu.kz.YandexEatsAnalogue.entity.OrderItem;
import sdu.edu.kz.YandexEatsAnalogue.service.OrderItemService;
import sdu.edu.kz.YandexEatsAnalogue.utils.ModelMapperUtil;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orderItems")
@RequiredArgsConstructor
public class OrderItemController {

    @Autowired
    private final OrderItemService orderItemService;

    @Autowired
    private final ModelMapperUtil modelMapperUtil;

    @GetMapping
    public ResponseEntity<List<OrderItemDTO>> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemService.findAllOrderItems();
        List<OrderItemDTO> orderItemDTOs = orderItems.stream()
                .map(orderItem -> modelMapperUtil.map(orderItem, OrderItemDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderItemDTOs);
    }

    @GetMapping("/{orderItemId}")
    public ResponseEntity<OrderItemDTO> getOrderItemById(@PathVariable Long orderItemId) {
        return orderItemService.findOrderItemById(orderItemId)
                .map(orderItem -> ResponseEntity.ok(modelMapperUtil.map(orderItem, OrderItemDTO.class)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<OrderItemDTO> createOrderItem(@RequestBody OrderItemDTO orderItemDTO) {
        OrderItem orderItemRequest = modelMapperUtil.map(orderItemDTO, OrderItem.class);
        OrderItem orderItem = orderItemService.saveOrderItem(orderItemRequest);
        return new ResponseEntity<>(modelMapperUtil.map(orderItem, OrderItemDTO.class), HttpStatus.CREATED);
    }

    @PutMapping("/{orderItemId}")
    public ResponseEntity<OrderItemDTO> updateOrderItem(@PathVariable Long orderItemId, @RequestBody OrderItemDTO orderItemDTO) {
        return orderItemService.findOrderItemById(orderItemId)
                .map(orderItem -> {
                    orderItem.setQuantity(orderItemDTO.getQuantity());
                    orderItem.setPriceAtOrderTime(orderItemDTO.getPriceAtOrderTime());

                    OrderItem updatedOrderItem = orderItemService.saveOrderItem(orderItem);
                    return ResponseEntity.ok(modelMapperUtil.map(updatedOrderItem, OrderItemDTO.class));
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{orderItemId}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long orderItemId) {
        orderItemService.deleteOrderItem(orderItemId);
        return ResponseEntity.noContent().build();
    }
}
