package sdu.edu.kz.YandexEatsAnalogue.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdu.edu.kz.YandexEatsAnalogue.dto.RestaurantRatingDTO;
import sdu.edu.kz.YandexEatsAnalogue.service.RestaurantRatingService;
import sdu.edu.kz.YandexEatsAnalogue.utils.ModelMapperUtil;

@RestController
@RequestMapping("/ratings")
@RequiredArgsConstructor
public class RestaurantRatingController {
    private final RestaurantRatingService restaurantRatingService;
    private final ModelMapperUtil modelMapperUtil;

    @GetMapping
    public ResponseEntity<?> getAllRatings() {
        return new ResponseEntity<>(restaurantRatingService.findAllRatings().stream()
                .map(restaurantRating -> modelMapperUtil.map(restaurantRating, RestaurantRatingDTO.class))
                .toArray(), HttpStatus.OK);
    }

    @GetMapping("/{ratingId}")
    public ResponseEntity<RestaurantRatingDTO> getRatingById(@PathVariable Long ratingId) {
        return restaurantRatingService.findRatingById(ratingId)
                .map(restaurantRating -> new ResponseEntity<>(
                        modelMapperUtil.map(restaurantRating, RestaurantRatingDTO.class),
                        HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<RestaurantRatingDTO> createRating(@RequestBody RestaurantRatingDTO ratingDTO) {
        restaurantRatingService.saveRating(modelMapperUtil.map(ratingDTO, RestaurantRatingDTO.class));
        return new ResponseEntity<>(ratingDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{ratingId}")
    public ResponseEntity<RestaurantRatingDTO> updateRating(@PathVariable Long ratingId,
            @RequestBody RestaurantRatingDTO ratingDTO) {
        return restaurantRatingService.findRatingById(ratingId)
                .map(existingRating -> {
                    restaurantRatingService.updateRating(ratingDTO, ratingId);
                    return new ResponseEntity<>(ratingDTO, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{ratingId}")
    public ResponseEntity<Void> deleteRating(@PathVariable Long ratingId) {
        return restaurantRatingService.findRatingById(ratingId)
                .map(existingRating -> {
                    restaurantRatingService.deleteRating(ratingId);
                    return new ResponseEntity<Void>(HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}