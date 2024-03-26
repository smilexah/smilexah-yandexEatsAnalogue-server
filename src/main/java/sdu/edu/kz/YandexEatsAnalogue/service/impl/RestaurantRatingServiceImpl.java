package sdu.edu.kz.YandexEatsAnalogue.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdu.edu.kz.YandexEatsAnalogue.entity.RestaurantRating;
import sdu.edu.kz.YandexEatsAnalogue.repository.RestaurantRatingRepository;
import sdu.edu.kz.YandexEatsAnalogue.service.RestaurantRatingService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantRatingServiceImpl implements RestaurantRatingService {
    @Autowired
    private final RestaurantRatingRepository restaurantRatingRepository;

    @Override
    public List<RestaurantRating> findAllRatings() {
        return restaurantRatingRepository.findAll();
    }

    @Override
    public Optional<RestaurantRating> findRatingById(Long id) {
        return restaurantRatingRepository.findById(id);
    }

    @Override
    public RestaurantRating saveRating(RestaurantRating rating) {
        return restaurantRatingRepository.save(rating);
    }

    @Override
    public void deleteRating(Long id) {
        restaurantRatingRepository.deleteById(id);
    }
}
