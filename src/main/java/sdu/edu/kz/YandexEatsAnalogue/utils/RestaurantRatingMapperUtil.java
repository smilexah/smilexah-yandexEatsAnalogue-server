package sdu.edu.kz.YandexEatsAnalogue.utils;

import sdu.edu.kz.YandexEatsAnalogue.dto.RestaurantRatingDTO;
import sdu.edu.kz.YandexEatsAnalogue.entity.RestaurantRating;

public class RestaurantRatingMapperUtil {
    public static RestaurantRating toEntity(RestaurantRatingDTO ratingDTO) {
        if (ratingDTO == null) {
            return null;
        }

        RestaurantRating rating = new RestaurantRating();

        rating.setId(ratingDTO.getRatingId());
        rating.setRestaurant(ratingDTO.getRestaurantId());
        rating.setCustomer(ratingDTO.getCustomerId());
        rating.setRating(ratingDTO.getRating());
        rating.setComment(ratingDTO.getComment());
        rating.setCreatedAt(ratingDTO.getCreatedAt());

        return rating;
    }

    public static RestaurantRatingDTO toDTO(RestaurantRating rating) {
        if (rating == null) {
            return null;
        }

        RestaurantRatingDTO ratingDTO = new RestaurantRatingDTO();

        ratingDTO.setRatingId(rating.getId());
        ratingDTO.setRestaurantId(rating.getRestaurant());
        ratingDTO.setCustomerId(rating.getCustomer());
        ratingDTO.setRating(rating.getRating());
        ratingDTO.setComment(rating.getComment());
        ratingDTO.setCreatedAt(rating.getCreatedAt());

        return ratingDTO;
    }
}
