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

    // Assume CustomMapper contains static methods for converting between entities and DTOs
    @Autowired
    private final ModelMapperUtil modelMapperUtil;

    @PutMapping("/{deliveryId}")
    public ResponseEntity<OrderDeliveryDTO> updateOrderDelivery(@PathVariable Long deliveryId, @RequestBody OrderDeliveryDTO orderDeliveryDTO) {
        return orderDeliveryService.findOrderDeliveryById(deliveryId)
                .map(orderDelivery -> {
                    // Manually update fields
                    // Assuming you have methods to convert IDs to entities
                    orderDelivery.setDeliveryPartner(modelMapperUtil.convertPartnerIdToEntity(orderDeliveryDTO.getPartnerId()));
                    orderDelivery.setOrder(modelMapperUtil.convertOrderIdToEntity(orderDeliveryDTO.getOrderId()));
                    orderDelivery.setPickupTime(orderDeliveryDTO.getPickupTime());
                    orderDelivery.setDeliveryTime(orderDeliveryDTO.getDeliveryTime());
                    orderDelivery.setStatus(orderDeliveryDTO.getStatus());

                    OrderDelivery updatedOrderDelivery = orderDeliveryService.saveOrderDelivery(orderDelivery);
                    return ResponseEntity.ok(modelMapperUtil.toOrderDeliveryDTO(updatedOrderDelivery));
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}