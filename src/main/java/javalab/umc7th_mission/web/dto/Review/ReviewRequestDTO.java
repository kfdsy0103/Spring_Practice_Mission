package javalab.umc7th_mission.web.dto.Review;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import javalab.umc7th_mission.validation.annotation.ExistMember;
import javalab.umc7th_mission.validation.annotation.ExistRestaurant;
import lombok.Getter;

public class ReviewRequestDTO {

    @Getter
    public static class JoinDto {
        @ExistRestaurant
        Long restaurantId;

        @ExistMember
        String memberEmail;

        @NotBlank
        String content;

        @NotNull
        @Min(0)
        @Max(5)
        Integer score;
    }
}
