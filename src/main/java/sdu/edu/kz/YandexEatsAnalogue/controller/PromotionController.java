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

    @GetMapping
    public ResponseEntity<?> getAllPromotions() {
        return new ResponseEntity<>(promotionService.findAllPromotions().stream()
                .map(promotion -> modelMapperUtil.toPromotionDTO(promotion))
                .toArray(), HttpStatus.OK);
    }

    @GetMapping("/{promotionId}")
    public ResponseEntity<PromotionDTO> getPromotionById(@PathVariable Long promotionId) {
        return promotionService.findPromotionById(promotionId)
                .map(promotion -> new ResponseEntity<>(modelMapperUtil.toPromotionDTO(promotion),
                        HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<PromotionDTO> createPromotion(@RequestBody PromotionDTO promotionDTO) {
        promotionService.savePromotion(modelMapperUtil.map(promotionDTO, PromotionDTO.class));
        return new ResponseEntity<>(promotionDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{promotionId}")
    public ResponseEntity<PromotionDTO> updatePromotion(@PathVariable Long promotionId,
            @RequestBody PromotionDTO promotionDTO) {
        promotionService.updatePromotion(promotionDTO, promotionId);
        return new ResponseEntity<>(promotionDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{promotionId}")
    public ResponseEntity<Void> deletePromotion(@PathVariable Long promotionId) {
        promotionService.deletePromotion(promotionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}