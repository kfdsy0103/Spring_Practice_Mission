package javalab.umc7th_mission.repository.MemberMissionRepository;

import javalab.umc7th_mission.domain.enums.MissionStatus;
import javalab.umc7th_mission.web.dto.MissionDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface MemberMissionRepositoryCustom {
    List<MissionDTO> findMissionByMemberAndStatus(Long memberId, MissionStatus status, Pageable pageable);

    Long countCompletedMissionByMemberAndRegion(Long memberId, String regionName);
}
