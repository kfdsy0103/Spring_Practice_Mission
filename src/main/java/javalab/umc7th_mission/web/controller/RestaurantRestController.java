package javalab.umc7th_mission.web.controller;

import jakarta.validation.Valid;
import javalab.umc7th_mission.apiPayload.ApiResponse;
import javalab.umc7th_mission.converter.RestaurantConverter;
import javalab.umc7th_mission.domain.Restaurant;
import javalab.umc7th_mission.service.RestaurantService.RestaurantCommandService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static javalab.umc7th_mission.web.dto.Restaurant.RestaurantRequestDTO.*;
import static javalab.umc7th_mission.web.dto.Restaurant.RestaurantResponseDTO.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantRestController {

    private final RestaurantCommandService restaurantCommandService;

    @PostMapping
    public ApiResponse<JoinResultDTO> join(@RequestBody @Valid JoinDto request){
        Restaurant restaurant = restaurantCommandService.joinRestaurant(request);
        return ApiResponse.onSuccess(RestaurantConverter.toJoinResultDTO(restaurant));
    }
}
