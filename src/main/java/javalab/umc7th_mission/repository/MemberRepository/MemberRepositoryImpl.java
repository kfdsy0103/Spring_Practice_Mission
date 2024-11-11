package javalab.umc7th_mission.repository.MemberRepository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import ext.javalab.umc7th_mission.domain.QMember;
import ext.javalab.umc7th_mission.domain.mapping.QMemberMission;
import javalab.umc7th_mission.web.dto.MyPageDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QMember member = QMember.member;

    @Override
    public MyPageDTO findMyPageById(Long id) {

        return jpaQueryFactory
                .select(Projections.constructor(
                        MyPageDTO.class,
                        member.name,
                        member.email,
                        member.point)
                )
                .from(member)
                .where(member.id.eq(id))
                .fetchOne();
    }
}
