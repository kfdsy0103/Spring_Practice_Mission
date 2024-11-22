package javalab.umc7th_mission.service.RestaurantService;

import javalab.umc7th_mission.converter.RestaurantConverter;
import javalab.umc7th_mission.domain.FoodCategory;
import javalab.umc7th_mission.domain.Region;
import javalab.umc7th_mission.domain.Restaurant;
import javalab.umc7th_mission.repository.FoodCategoryRepository.FoodCategoryRepository;
import javalab.umc7th_mission.repository.RegionRepository.RegionRepository;
import javalab.umc7th_mission.repository.RestaurantRepository.RestaurantRepository;
import javalab.umc7th_mission.web.dto.Restaurant.RestaurantRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RestaurantCommandServiceImpl implements RestaurantCommandService {

    private final RestaurantRepository restaurantRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final RegionRepository regionRepository;

    @Override
    @Transactional
    public Restaurant joinRestaurant(RestaurantRequestDTO.JoinDto request) {
        Restaurant newRestaurant = RestaurantConverter.toRestaurant(request);
        Region region = regionRepository.findByName(request.getRegionName()).get();
        FoodCategory foodCategory = foodCategoryRepository.findById(request.getFoodCategory()).get();

        // 연관 관계 설정
        newRestaurant.setRegion(region);
        newRestaurant.setFoodCategory(foodCategory);

        return restaurantRepository.save(newRestaurant);
    }
}
