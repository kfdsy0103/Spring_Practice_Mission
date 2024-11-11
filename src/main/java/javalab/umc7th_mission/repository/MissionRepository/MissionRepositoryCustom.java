package javalab.umc7th_mission.repository.MissionRepository;

import javalab.umc7th_mission.web.dto.MissionDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MissionRepositoryCustom {
    List<MissionDTO> findMissionsNotInMemberMission(Long memberId, Pageable pageable);
}
