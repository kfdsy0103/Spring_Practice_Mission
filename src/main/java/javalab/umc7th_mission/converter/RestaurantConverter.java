package javalab.umc7th_mission.converter;

import javalab.umc7th_mission.domain.Restaurant;
import javalab.umc7th_mission.web.dto.Restaurant.RestaurantRequestDTO;
import javalab.umc7th_mission.web.dto.Restaurant.RestaurantResponseDTO;

public class RestaurantConverter {

    public static RestaurantResponseDTO.JoinResultDTO toJoinResultDTO(Restaurant restaurant){
        return RestaurantResponseDTO.JoinResultDTO.builder()
                .restaurantId(restaurant.getId())
                .createdAt(restaurant.getCreatedAt())
                .build();
    }

    public static Restaurant toRestaurant(RestaurantRequestDTO.JoinDto request){
        return Restaurant.builder()
                .name(request.getRestaurantName())
                .score(0.0f)
                .build();
    }
}
