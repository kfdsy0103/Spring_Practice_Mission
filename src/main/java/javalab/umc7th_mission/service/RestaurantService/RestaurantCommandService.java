package javalab.umc7th_mission.service.RestaurantService;

import javalab.umc7th_mission.domain.Restaurant;
import javalab.umc7th_mission.web.dto.Restaurant.RestaurantRequestDTO;

public interface RestaurantCommandService {
    Restaurant joinRestaurant(RestaurantRequestDTO.JoinDto request);
}
