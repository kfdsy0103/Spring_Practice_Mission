package javalab.umc7th_mission.repository.MissionRepository;

import javalab.umc7th_mission.domain.Mission;
import javalab.umc7th_mission.domain.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long>, MissionRepositoryCustom {
    @Query("""
        SELECT m
        FROM Mission m
        WHERE m.restaurant.id = :restaurantId
        AND m.id NOT IN (
            SELECT mm.mission.id
            FROM MemberMission mm
            WHERE mm.member.id = :memberId
        )
    """)
    Page<Mission> findAllNotInMemberMission(@Param("restaurantId") Long restaurantId,
                                            @Param("memberId") Long memberId,
                                            Pageable pageable);
}
