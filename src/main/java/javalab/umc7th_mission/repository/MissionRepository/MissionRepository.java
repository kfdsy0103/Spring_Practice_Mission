package javalab.umc7th_mission.repository.MissionRepository;

import javalab.umc7th_mission.domain.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long>, MissionRepositoryCustom {
}
