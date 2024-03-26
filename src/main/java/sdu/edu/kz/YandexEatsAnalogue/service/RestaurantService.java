package sdu.edu.kz.YandexEatsAnalogue.service;

import org.springframework.stereotype.Service;
import sdu.edu.kz.YandexEatsAnalogue.dto.RestaurantDTO;
import sdu.edu.kz.YandexEatsAnalogue.entity.Restaurant;

import java.util.List;

@Service
public interface RestaurantService {
    void createRestaurant(Restaurant restaurant);
    void updateRestaurant(Restaurant restaurant);
    void deleteRestaurant(Long restaurantId);
    Restaurant getRestaurant(Long restaurantId);
    List<Restaurant> getAllRestaurants();
}
