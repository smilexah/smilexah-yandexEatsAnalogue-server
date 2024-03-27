package sdu.edu.kz.YandexEatsAnalogue.service;

import org.springframework.stereotype.Service;
import sdu.edu.kz.YandexEatsAnalogue.dto.PromotionDTO;
import sdu.edu.kz.YandexEatsAnalogue.entity.Promotion;

import java.util.List;
import java.util.Optional;

@Service
public interface PromotionService {
    List<Promotion> findAllPromotions();
    Optional<Promotion> findPromotionById(Long id);
    void savePromotion(PromotionDTO promotionDTO);
    void updatePromotion(PromotionDTO promotionDTO, Long promotionId);
    void deletePromotion(Long id);
}
