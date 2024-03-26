package sdu.edu.kz.YandexEatsAnalogue.service;

import org.springframework.stereotype.Service;
import sdu.edu.kz.YandexEatsAnalogue.entity.RestaurantRating;

import java.util.List;
import java.util.Optional;

@Service
public interface RestaurantRatingService {
    List<RestaurantRating> findAllRatings();
    Optional<RestaurantRating> findRatingById(Long id);
    RestaurantRating saveRating(RestaurantRating rating);
    void deleteRating(Long id);
}
