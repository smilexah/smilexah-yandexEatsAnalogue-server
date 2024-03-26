package sdu.edu.kz.YandexEatsAnalogue.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdu.edu.kz.YandexEatsAnalogue.dto.RestaurantRatingDTO;
import sdu.edu.kz.YandexEatsAnalogue.entity.Restaurant;
import sdu.edu.kz.YandexEatsAnalogue.entity.RestaurantRating;
import sdu.edu.kz.YandexEatsAnalogue.service.RestaurantRatingService;
import sdu.edu.kz.YandexEatsAnalogue.utils.ModelMapperUtil;
import sdu.edu.kz.YandexEatsAnalogue.utils.RestaurantRatingMapperUtil;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/ratings")
@RequiredArgsConstructor
public class RestaurantRatingController {

    @Autowired
    private RestaurantRatingService restaurantRatingService;

    @Autowired
    private ModelMapperUtil modelMapperUtil;


    @PostMapping
    public ResponseEntity<RestaurantRatingDTO> createRating(@RequestBody RestaurantRatingDTO ratingDTO) {
        RestaurantRating restaurantRating = new RestaurantRating();
        restaurantRating.setRating(ratingDTO.getRating());
        restaurantRating.setComment(ratingDTO.getComment());
        restaurantRating.setCreatedAt(ratingDTO.getCreatedAt());

        // Fetch the Restaurant entity using reviewDTO.getRestaurantId()
        Restaurant restaurant = restaurantRatingService.findRatingById(ratingDTO.getRestaurantId().getId())
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found with id " + ratingDTO.getRestaurantId())).getRestaurant();
        restaurantRating.setRestaurant(restaurant);

        restaurantRatingService.saveRating(restaurantRating);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{ratingId}")
    public ResponseEntity<RestaurantRatingDTO> updateRating(@PathVariable Long ratingId, @RequestBody RestaurantRatingDTO ratingDTO) {
        return restaurantRatingService.findRatingById(ratingId)
                .map(existingRating -> {
                    existingRating.setRating(ratingDTO.getRating());
                    existingRating.setComment(ratingDTO.getComment());
                    // Note: createdAt should generally not be updated, so it's omitted here
                    RestaurantRating updatedRating = restaurantRatingService.saveRating(existingRating);
                    return ResponseEntity.ok(modelMapperUtil.map(updatedRating, RestaurantRatingDTO.class));
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}