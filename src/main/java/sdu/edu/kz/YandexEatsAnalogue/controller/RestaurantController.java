package sdu.edu.kz.YandexEatsAnalogue.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdu.edu.kz.YandexEatsAnalogue.dto.RestaurantDTO;
import sdu.edu.kz.YandexEatsAnalogue.entity.Restaurant;
import sdu.edu.kz.YandexEatsAnalogue.entity.RestaurantRating;
import sdu.edu.kz.YandexEatsAnalogue.service.RestaurantService;
import sdu.edu.kz.YandexEatsAnalogue.utils.ModelMapperUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final ModelMapperUtil modelMapperUtil;

    @GetMapping
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants() {
        return new ResponseEntity<>(restaurantService.findAllRestaurant().stream()
                .map(restaurant -> modelMapperUtil.map(restaurant, RestaurantDTO.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurantById(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.findRestaurantById(id)
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException(
                        "Restaurant with ID " + id + " not found"));

        if (restaurant.getRatings() == null) {
            restaurant.setRatings((List<RestaurantRating>) BigDecimal.ZERO);
        }

        BigDecimal averageRating = restaurant.calculateAverageRating();
        RestaurantDTO restaurantDTO = modelMapperUtil.map(restaurant, RestaurantDTO.class);
        restaurantDTO.setRating(averageRating);

        return new ResponseEntity<>(restaurantDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RestaurantDTO> createRestaurant(@Valid @RequestBody RestaurantDTO restaurantDTO) {
        restaurantService.saveRestaurant(modelMapperUtil.map(restaurantDTO, RestaurantDTO.class));
        return new ResponseEntity<>(restaurantDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDTO> updateRestaurant(@PathVariable Long id,
            @Valid @RequestBody RestaurantDTO restaurantDTO) {
        restaurantService.updateRestaurant(restaurantDTO, id);
        return new ResponseEntity<>(restaurantDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
