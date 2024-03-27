package sdu.edu.kz.YandexEatsAnalogue.service;

import org.springframework.stereotype.Service;
import sdu.edu.kz.YandexEatsAnalogue.dto.OrderItemDTO;
import sdu.edu.kz.YandexEatsAnalogue.dto.RestaurantDTO;
import sdu.edu.kz.YandexEatsAnalogue.entity.OrderItem;
import sdu.edu.kz.YandexEatsAnalogue.entity.Restaurant;

import java.util.List;
import java.util.Optional;

@Service
public interface RestaurantService {
    List<Restaurant> findAllRestaurant();
    Optional<Restaurant> findRestaurantById(Long id);
    void saveRestaurant(RestaurantDTO restaurantDTO);
    void updateRestaurant(RestaurantDTO restaurantDTO, Long id);
    void deleteRestaurant(Long id);
}
