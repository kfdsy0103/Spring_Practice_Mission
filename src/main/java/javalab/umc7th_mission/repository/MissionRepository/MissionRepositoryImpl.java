package javalab.umc7th_mission.repository.MissionRepository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import ext.javalab.umc7th_mission.domain.QMission;
import ext.javalab.umc7th_mission.domain.QRestaurant;
import ext.javalab.umc7th_mission.domain.mapping.QMemberMission;
import javalab.umc7th_mission.web.dto.MissionDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;
    private final QMission mission = QMission.mission;

    @Override
    public List<MissionDTO> findMissionsNotInMemberMission(Long memberId, Pageable pageable) {

        QRestaurant restaurant = QRestaurant.restaurant;
        QMemberMission memberMission = QMemberMission.memberMission;

        return jpaQueryFactory
                .select(Projections.constructor(
                        MissionDTO.class,
                        mission.restaurant.id,
                        restaurant.name,
                        mission.content,
                        mission.point)
                )
                .from(mission)
                .join(mission.restaurant, restaurant)
                .where(mission.id.notIn(
                        JPAExpressions
                                .select(memberMission.mission.id)
                                .from(memberMission)
                                .where(memberMission.member.id.eq(memberId))
                ))
                .orderBy(mission.deadline.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
