package sdu.edu.kz.YandexEatsAnalogue.service;

import org.springframework.stereotype.Service;
import sdu.edu.kz.YandexEatsAnalogue.dto.PromotionDTO;
import sdu.edu.kz.YandexEatsAnalogue.entity.Promotion;

import java.util.List;
import java.util.Optional;

@Service
public interface PromotionService {
    Promotion savePromotion(Promotion promotion);
//    void updatePromotion(PromotionDTO promotionDTO);
    void deletePromotion(Long id);
    Optional<Promotion> findPromotionById(Long id);
    List<Promotion> findAllPromotions();
}
