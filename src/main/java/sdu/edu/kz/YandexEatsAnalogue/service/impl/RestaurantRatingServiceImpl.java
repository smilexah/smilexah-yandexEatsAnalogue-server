package sdu.edu.kz.YandexEatsAnalogue.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sdu.edu.kz.YandexEatsAnalogue.dto.RestaurantRatingDTO;
import sdu.edu.kz.YandexEatsAnalogue.entity.Customer;
import sdu.edu.kz.YandexEatsAnalogue.entity.Restaurant;
import sdu.edu.kz.YandexEatsAnalogue.entity.RestaurantRating;
import sdu.edu.kz.YandexEatsAnalogue.repository.RestaurantRatingRepository;
import sdu.edu.kz.YandexEatsAnalogue.service.RestaurantRatingService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantRatingServiceImpl implements RestaurantRatingService {
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
    public void saveRating(RestaurantRatingDTO ratingDTO) {
        RestaurantRating restaurantRating = new RestaurantRating();

        Restaurant restaurant = new Restaurant();
        restaurant.setId(ratingDTO.getRestaurantId());
        restaurantRating.setRestaurant(restaurant);

        Customer customer = new Customer();
        customer.setCustomerId(ratingDTO.getCustomerId());
        restaurantRating.setCustomer(customer);

        restaurantRating.setRating(ratingDTO.getRating());
        restaurantRating.setComment(ratingDTO.getComment());
        restaurantRating.setCreatedAt(ratingDTO.getCreatedAt());

        restaurantRatingRepository.save(restaurantRating);
    }

    @Override
    public void updateRating(RestaurantRatingDTO ratingDTO, Long id) {
        Optional<RestaurantRating> ratingOptional = restaurantRatingRepository.findById(id);
        if (ratingOptional.isEmpty()) {
            throw new EntityNotFoundException("Rating not found");
        }
        RestaurantRating restaurantRating = ratingOptional.get();

        restaurantRating.setRating(ratingDTO.getRating());
        restaurantRating.setComment(ratingDTO.getComment());
        restaurantRating.setCreatedAt(ratingDTO.getCreatedAt());

        restaurantRatingRepository.save(restaurantRating);
    }

    @Override
    public void deleteRating(Long id) {
        restaurantRatingRepository.deleteById(id);
    }
}
