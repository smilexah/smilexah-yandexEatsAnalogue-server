package sdu.edu.kz.YandexEatsAnalogue.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdu.edu.kz.YandexEatsAnalogue.entity.Restaurant;
import sdu.edu.kz.YandexEatsAnalogue.repository.RestaurantRepository;
import sdu.edu.kz.YandexEatsAnalogue.service.RestaurantService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private final RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public void createRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    @Override
    public void updateRestaurant(Restaurant updatedRestaurant) {
        Restaurant restaurant = restaurantRepository.findById(updatedRestaurant.getId())
                .orElseThrow(() -> new EntityNotFoundException("Restaurant with ID " + updatedRestaurant.getId() + " not found"));

        restaurant.setName(updatedRestaurant.getName());
        restaurant.setAddress(updatedRestaurant.getAddress());
        restaurant.setRating(updatedRestaurant.getRating());
        restaurant.setDeliveryTimeEstimate(updatedRestaurant.getDeliveryTimeEstimate());
        restaurant.setIsActive(updatedRestaurant.getIsActive());

        restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) {
        restaurantRepository.deleteById(restaurantId);
    }

    @Override
    public Restaurant getRestaurant(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant with ID " + restaurantId + " not found"));
    }
}
