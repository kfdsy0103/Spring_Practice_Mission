package javalab.umc7th_mission.repository.ReviewRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import ext.javalab.umc7th_mission.domain.QReview;
import ext.javalab.umc7th_mission.domain.mapping.QMemberMission;
import javalab.umc7th_mission.domain.Member;
import javalab.umc7th_mission.domain.Restaurant;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QReview review = QReview.review;

    @Override
    public Long insertReview(Long memberId, Long restaurantId, String content, String score) {

        return jpaQueryFactory
                .insert(review)
                .columns(review.member, review.restaurant, review.content, review.score)
                .values(memberId, restaurantId, content, score)
                .execute();
    }
}
