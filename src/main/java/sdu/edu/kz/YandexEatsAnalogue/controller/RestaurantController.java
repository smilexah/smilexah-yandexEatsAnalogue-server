package sdu.edu.kz.YandexEatsAnalogue.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdu.edu.kz.YandexEatsAnalogue.dto.RestaurantDTO;
import sdu.edu.kz.YandexEatsAnalogue.entity.Restaurant;
import sdu.edu.kz.YandexEatsAnalogue.service.RestaurantService;
import sdu.edu.kz.YandexEatsAnalogue.utils.ModelMapperUtil;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    @Autowired
    private final RestaurantService restaurantService;
    @Autowired
    private final ModelMapperUtil modelMapperUtil;

    @GetMapping
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        List<RestaurantDTO> restaurantDTOs = restaurants.stream()
                .map(restaurant -> modelMapperUtil.map(restaurant, RestaurantDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(restaurantDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurant(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.getRestaurant(id);
        RestaurantDTO restaurantDTO = modelMapperUtil.mapToRestaurantDTO(restaurant);
        return ResponseEntity.ok(restaurantDTO);

//        return restaurantService.getRestaurant(id)
//                .map(restaurant -> ResponseEntity.ok(modelMapperUtil.map(restaurant, RestaurantDTO.class)))
//                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<RestaurantDTO> createRestaurant(@Valid @RequestBody RestaurantDTO restaurantDTO) {
        Restaurant restaurant = modelMapperUtil.mapToRestaurantEntity(restaurantDTO);
        restaurantService.createRestaurant(restaurant);
        return new ResponseEntity<>(restaurantDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDTO> updateRestaurant(@PathVariable Long id, @Valid @RequestBody RestaurantDTO restaurantDTO) {
        Restaurant restaurant = modelMapperUtil.mapToRestaurantEntity(restaurantDTO);
        restaurant.setId(id);
        restaurantService.updateRestaurant(restaurant);
        return ResponseEntity.ok(restaurantDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.noContent().build();
    }
}
