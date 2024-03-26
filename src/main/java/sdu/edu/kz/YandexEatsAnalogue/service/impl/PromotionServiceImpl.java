package sdu.edu.kz.YandexEatsAnalogue.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public Promotion savePromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    @Override
    public void deletePromotion(Long id) {
        promotionRepository.deleteById(id);
    }

}
