package sdu.edu.kz.YandexEatsAnalogue.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sdu.edu.kz.YandexEatsAnalogue.dto.RestaurantDTO;
import sdu.edu.kz.YandexEatsAnalogue.entity.Restaurant;
import sdu.edu.kz.YandexEatsAnalogue.repository.RestaurantRepository;
import sdu.edu.kz.YandexEatsAnalogue.service.RestaurantService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> findAllRestaurant() {
        return restaurantRepository.findAll();
    }

    @Override
    public Optional<Restaurant> findRestaurantById(Long id) {
        return Optional.ofNullable(restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant with ID " + id + " not found")));
    }

    @Override
    public void saveRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = new Restaurant();

        restaurant.setName(restaurantDTO.getName());
        restaurant.setAddress(restaurantDTO.getAddress());
        restaurant.setRating(restaurantDTO.getRating());
        restaurant.setDeliveryTimeEstimate(restaurantDTO.getDeliveryTimeEstimate());
        restaurant.setIsActive(restaurantDTO.getIsActive());

        restaurantRepository.save(restaurant);
    }

    @Override
    public void updateRestaurant(RestaurantDTO restaurantDTO, Long id) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
        if (restaurantOptional.isEmpty()) {
            throw new EntityNotFoundException("Restaurant not found");
        }
        Restaurant restaurant = restaurantOptional.get();

        restaurant.setName(restaurantDTO.getName());
        restaurant.setAddress(restaurantDTO.getAddress());
        restaurant.setRating(restaurantDTO.getRating());
        restaurant.setDeliveryTimeEstimate(restaurantDTO.getDeliveryTimeEstimate());
        restaurant.setIsActive(restaurantDTO.getIsActive());

        restaurantRepository.save(restaurant);
    }

    @Override
    @Transactional
    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }
}
