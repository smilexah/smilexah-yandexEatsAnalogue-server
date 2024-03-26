package sdu.edu.kz.YandexEatsAnalogue.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdu.edu.kz.YandexEatsAnalogue.dto.PromotionDTO;
import sdu.edu.kz.YandexEatsAnalogue.entity.Promotion;
import sdu.edu.kz.YandexEatsAnalogue.service.PromotionService;
import sdu.edu.kz.YandexEatsAnalogue.utils.ModelMapperUtil;

@RestController
@RequestMapping("/promotions")
@RequiredArgsConstructor
public class PromotionController {

    @Autowired
    private final PromotionService promotionService;

    @Autowired
    private final ModelMapperUtil modelMapperUtil;

    @PostMapping
    public ResponseEntity<PromotionDTO> createPromotion(@RequestBody PromotionDTO promotionDTO) {
        Promotion promotion = modelMapperUtil.toPromotionEntity(promotionDTO);
        Promotion savedPromotion = promotionService.savePromotion(promotion);
        return new ResponseEntity<>(modelMapperUtil.toPromotionDTO(savedPromotion), HttpStatus.CREATED);
    }

    @PutMapping("/{promotionId}")
    public ResponseEntity<PromotionDTO> updatePromotion(@PathVariable Long promotionId, @RequestBody PromotionDTO promotionDTO) {
        return promotionService.findPromotionById(promotionId)
                .map(existingPromotion -> {
                    modelMapperUtil.updatePromotionFromDTO(promotionDTO, existingPromotion);
                    Promotion updatedPromotion = promotionService.savePromotion(existingPromotion);
                    return ResponseEntity.ok(modelMapperUtil.toPromotionDTO(updatedPromotion));
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}