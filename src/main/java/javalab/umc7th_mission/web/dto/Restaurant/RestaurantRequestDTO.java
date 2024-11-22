package javalab.umc7th_mission.web.dto.Restaurant;

import jakarta.validation.constraints.NotBlank;
import javalab.umc7th_mission.validation.annotation.ExistCategory;
import javalab.umc7th_mission.validation.annotation.ExistRegion;
import lombok.Getter;

public class RestaurantRequestDTO {

    @Getter
    public static class JoinDto {
        @NotBlank
        String restaurantName;

        @ExistRegion
        String regionName;

        @ExistCategory
        Long foodCategory;
    }
}
