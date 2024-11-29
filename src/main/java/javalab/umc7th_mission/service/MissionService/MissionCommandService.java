package javalab.umc7th_mission.service.MissionService;

import javalab.umc7th_mission.domain.Mission;
import javalab.umc7th_mission.domain.mapping.MemberMission;
import javalab.umc7th_mission.web.dto.Mission.MissionRequestDTO;

public interface MissionCommandService {
    Mission joinMission(MissionRequestDTO.JoinDto request);
    MemberMission challengeMission(MissionRequestDTO.ChallengeDto request);
    MemberMission completeMission(MissionRequestDTO.CompleteDto request);
}
