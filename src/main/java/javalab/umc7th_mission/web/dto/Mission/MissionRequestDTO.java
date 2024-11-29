package javalab.umc7th_mission.web.dto.Mission;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import javalab.umc7th_mission.domain.enums.MissionStatus;
import javalab.umc7th_mission.validation.annotation.*;
import lombok.Getter;

import java.time.LocalDate;

public class MissionRequestDTO {

    @Getter
    public static class JoinDto {
        @ExistRestaurant
        Long restaurantId;

        @NotBlank
        String content;

        @NotNull
        Integer point;

        @NotNull
        LocalDate deadline;
    }

    @Getter
    @ExistChallenge
    public static class ChallengeDto {
        @NotNull
        Long missionId;

        @NotBlank
        String memberEmail;

        @NotNull
        MissionStatus status;
    }

    @Getter
    public static class CompleteDto {
        @NotNull
        Long memberMissionId;
        @ExistMember
        String memberEmail;
    }
}
