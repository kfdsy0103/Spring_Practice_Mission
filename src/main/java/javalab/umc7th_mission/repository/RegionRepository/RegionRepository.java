package javalab.umc7th_mission.repository.RegionRepository;

import javalab.umc7th_mission.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {
    boolean existsByName(String name);
    Optional<Region> findByName(String name);
}
