package sdu.edu.kz.YandexEatsAnalogue.controller;

import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdu.edu.kz.YandexEatsAnalogue.dto.OrderPromotionDTO;
import sdu.edu.kz.YandexEatsAnalogue.entity.OrderPromotion;
import sdu.edu.kz.YandexEatsAnalogue.service.OrderPromotionService;
import sdu.edu.kz.YandexEatsAnalogue.utils.ModelMapperUtil;

@RestController
@RequestMapping("/orderPromotions")
@RequiredArgsConstructor
public class OrderPromotionController {
    private final OrderPromotionService orderPromotionService;
    private final ModelMapperUtil modelMapperUtil;

    @GetMapping
    public ResponseEntity<?> getAllOrderPromotions() {
        return new ResponseEntity<>(orderPromotionService.findAllOrderPromotions().stream()
                .map(orderPromotion -> modelMapperUtil.map(orderPromotion, OrderPromotionDTO.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{orderPromotionId}")
    public ResponseEntity<OrderPromotionDTO> getOrderPromotionById(@PathVariable Long orderPromotionId) {
        return orderPromotionService.findOrderPromotionById(orderPromotionId)
                .map(orderPromotion -> new ResponseEntity<>(
                        modelMapperUtil.map(orderPromotion, OrderPromotionDTO.class),
                        HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<OrderPromotionDTO> createOrderPromotion(@RequestBody OrderPromotionDTO orderPromotionDTO) {
        orderPromotionService.saveOrderPromotion(modelMapperUtil.map(orderPromotionDTO, OrderPromotionDTO.class));
        return new ResponseEntity<>(orderPromotionDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{orderPromotionId}")
    public ResponseEntity<OrderPromotionDTO> updateOrderPromotion(@PathVariable Long orderPromotionId,
            @RequestBody OrderPromotionDTO orderPromotionDTO) {
        orderPromotionService.updateOrderPromotion(orderPromotionDTO, orderPromotionId);
        return new ResponseEntity<>(orderPromotionDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{orderPromotionId}")
    public ResponseEntity<Void> deleteOrderPromotion(@PathVariable Long orderPromotionId) {
        orderPromotionService.deleteOrderPromotion(orderPromotionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}