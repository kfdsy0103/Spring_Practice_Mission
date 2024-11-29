package javalab.umc7th_mission.service.MissionService;

import javalab.umc7th_mission.domain.Mission;
import javalab.umc7th_mission.domain.enums.MissionStatus;
import javalab.umc7th_mission.domain.mapping.MemberMission;
import org.springframework.data.domain.Page;

public interface MissionQueryService {

    Page<Mission> getRestaurantMissionList(Long restaurantId, Long memberId, Integer page);
    Page<MemberMission> getMemberMissionList(Long memberId, MissionStatus status, Integer page);
}
