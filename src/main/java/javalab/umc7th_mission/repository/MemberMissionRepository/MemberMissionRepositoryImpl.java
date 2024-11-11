package javalab.umc7th_mission.repository.MemberMissionRepository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import ext.javalab.umc7th_mission.domain.QMission;
import ext.javalab.umc7th_mission.domain.QRegion;
import ext.javalab.umc7th_mission.domain.QRestaurant;
import ext.javalab.umc7th_mission.domain.mapping.QMemberMission;
import javalab.umc7th_mission.domain.enums.MissionStatus;
import javalab.umc7th_mission.web.dto.MissionDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class MemberMissionRepositoryImpl implements MemberMissionRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QMemberMission memberMission = QMemberMission.memberMission;

    @Override
    public List<MissionDTO> findMissionByMemberAndStatus(Long memberId, MissionStatus status, Pageable pageable) {

        QMission mission = QMission.mission;
        QRestaurant restaurant = QRestaurant.restaurant;

        return jpaQueryFactory
                .select(Projections.constructor(
                        MissionDTO.class,
                        mission.restaurant.id,
                        restaurant.name,
                        mission.content,
                        mission.point)
                )
                .from(memberMission)
                .join(memberMission.mission, mission)
                .join(mission.restaurant, restaurant)
                .where(
                        memberIdEq(memberId),
                        statusEq(status)
                )
                .orderBy(memberMission.mission.deadline.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public Long countCompletedMissionByMemberAndRegion(Long memberId, String regionName) {
        QMission mission = QMission.mission;
        QRegion region = QRegion.region;

        return jpaQueryFactory
                .select(memberMission.count())
                .from(memberMission)
                .join(memberMission.mission, mission)
                .join(mission.region, region)
                .where(
                        memberIdEq(memberId),
                        statusEq(MissionStatus.COMPLETE),
                        region.name.eq(regionName)
                )
                .fetchOne();
    }

    private BooleanExpression memberIdEq(Long memberId) {
        return memberId != null ? memberMission.member.id.eq(memberId) : null;
    }

    private BooleanExpression statusEq(MissionStatus status) {
        return status != null ? memberMission.status.eq(status) : null;
    }

}
