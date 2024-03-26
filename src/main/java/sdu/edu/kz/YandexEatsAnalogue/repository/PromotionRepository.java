package sdu.edu.kz.YandexEatsAnalogue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sdu.edu.kz.YandexEatsAnalogue.entity.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
}
