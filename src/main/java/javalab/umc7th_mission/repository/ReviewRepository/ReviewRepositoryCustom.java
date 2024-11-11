package javalab.umc7th_mission.repository.ReviewRepository;

import javalab.umc7th_mission.domain.Member;
import javalab.umc7th_mission.domain.Restaurant;

public interface ReviewRepositoryCustom {

    Long insertReview(Long memberId, Long restaurantId, String content, String score);
}
