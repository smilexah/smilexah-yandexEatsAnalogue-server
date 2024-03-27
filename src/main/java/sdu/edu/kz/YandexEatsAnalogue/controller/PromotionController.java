package sdu.edu.kz.YandexEatsAnalogue.controller;

import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sdu.edu.kz.YandexEatsAnalogue.dto.CustomerDTO;
import sdu.edu.kz.YandexEatsAnalogue.dto.PromotionDTO;
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
                .map(promotion -> modelMapperUtil.map(promotion, PromotionDTO.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{promotionId}")
    public ResponseEntity<PromotionDTO> getPromotionById(@PathVariable Long promotionId) {
        return promotionService.findPromotionById(promotionId)
                .map(promotion -> new ResponseEntity<>(modelMapperUtil.map(promotion, PromotionDTO.class),
                        HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<PromotionDTO> createPromotion(@RequestBody PromotionDTO promotionDTO) {
        promotionService.savePromotion(promotionDTO);
        return new ResponseEntity<>(promotionDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{promotionId}")
    public ResponseEntity<?> updatePromotion(@PathVariable Long promotionId,
            @RequestBody PromotionDTO promotionDTO) {
        return promotionService.findPromotionById(promotionId)
                .map(promotion -> {
                    promotionService.updatePromotion(promotionDTO, promotionId);
                    return new ResponseEntity<>(HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{promotionId}")
    public ResponseEntity<Void> deletePromotion(@PathVariable Long promotionId) {
        return promotionService.findPromotionById(promotionId)
                .map(promotion -> {
                    promotionService.deletePromotion(promotionId);
                    return new ResponseEntity<Void>(HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}