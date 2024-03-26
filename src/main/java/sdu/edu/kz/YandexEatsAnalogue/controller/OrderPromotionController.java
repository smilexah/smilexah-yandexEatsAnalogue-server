package sdu.edu.kz.YandexEatsAnalogue.controller;

import lombok.RequiredArgsConstructor;
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

    @Autowired
    private final OrderPromotionService orderPromotionService;

    @Autowired
    private final ModelMapperUtil modelMapperUtil;

    @PostMapping
    public ResponseEntity<OrderPromotionDTO> createOrderPromotion(@RequestBody OrderPromotionDTO orderPromotionDTO) {
        OrderPromotion orderPromotion = modelMapperUtil.toOrderPromotionEntity(orderPromotionDTO);
        OrderPromotion savedOrderPromotion = orderPromotionService.saveOrderPromotion(orderPromotion);
        return new ResponseEntity<>(modelMapperUtil.toOrderPromotionDTO(savedOrderPromotion), HttpStatus.CREATED);
    }

    @PutMapping("/{orderPromotionId}")
    public ResponseEntity<OrderPromotionDTO> updateOrderPromotion(@PathVariable Long orderPromotionId, @RequestBody OrderPromotionDTO orderPromotionDTO) {
        return orderPromotionService.findOrderPromotionById(orderPromotionId)
                .map(existingOrderPromotion -> {
                    modelMapperUtil.updateOrderPromotionFromDTO(orderPromotionDTO, existingOrderPromotion);
                    OrderPromotion updatedOrderPromotion = orderPromotionService.saveOrderPromotion(existingOrderPromotion);
                    return ResponseEntity.ok(modelMapperUtil.toOrderPromotionDTO(updatedOrderPromotion));
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}