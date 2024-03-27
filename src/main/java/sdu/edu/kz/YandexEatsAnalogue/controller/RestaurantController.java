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
        return new ResponseEntity<>(restaurantService.findAllRestaurant().stream()
                .map(modelMapperUtil::mapToRestaurantDTO)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurant(@PathVariable Long id) {
        return restaurantService.findRestaurantById(id)
                .map(restaurant -> new ResponseEntity<>(modelMapperUtil.mapToRestaurantDTO(restaurant), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
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
