package sdu.edu.kz.YandexEatsAnalogue.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import sdu.edu.kz.YandexEatsAnalogue.dto.PromotionDTO;
import sdu.edu.kz.YandexEatsAnalogue.entity.Promotion;
import sdu.edu.kz.YandexEatsAnalogue.repository.PromotionRepository;
import sdu.edu.kz.YandexEatsAnalogue.service.PromotionService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {
    @Autowired
    private final PromotionRepository promotionRepository;

    @Override
    public List<Promotion> findAllPromotions() {
        return promotionRepository.findAll();
    }

    @Override
    public Optional<Promotion> findPromotionById(Long id) {
        return promotionRepository.findById(id);
    }

    @Override
    public void savePromotion(PromotionDTO promotionDTO) {
        Promotion promotion = new Promotion();

        promotion.setName(promotionDTO.getName());
        promotion.setDescription(promotionDTO.getDescription());
        promotion.setDiscountPercent(promotionDTO.getDiscountPercent());
        promotion.setStartDate(promotionDTO.getStartDate());
        promotion.setEndDate(promotionDTO.getEndDate());
        promotion.setIsActive(promotionDTO.getIsActive());

        promotionRepository.save(promotion);
    }

    @Override
    public void updatePromotion(PromotionDTO promotionDTO, Long promotionId) {
        Optional<Promotion> promotionOptional = promotionRepository.findById(promotionId);
        if (promotionOptional.isEmpty()) {
            throw new EntityNotFoundException("Promotion not found");
        }
        Promotion promotion = promotionOptional.get();

        promotion.setName(promotionDTO.getName());
        promotion.setDescription(promotionDTO.getDescription());
        promotion.setDiscountPercent(promotionDTO.getDiscountPercent());
        promotion.setStartDate(promotionDTO.getStartDate());
        promotion.setEndDate(promotionDTO.getEndDate());
        promotion.setIsActive(promotionDTO.getIsActive());

        promotionRepository.save(promotion);
    }

    @Override
    public void deletePromotion(Long id) {
        promotionRepository.deleteById(id);
    }

}
