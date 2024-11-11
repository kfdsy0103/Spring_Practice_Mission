package javalab.umc7th_mission.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MissionDTO {
    private Long missionId;
    private String restaurantName;
    private String content;
    private Integer point;
}
