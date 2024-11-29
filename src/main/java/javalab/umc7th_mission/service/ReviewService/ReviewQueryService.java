package javalab.umc7th_mission.service.ReviewService;

import javalab.umc7th_mission.domain.Review;
import org.springframework.data.domain.Page;

public interface ReviewQueryService {
    Page<Review> getMemberReviewList(Long restaurantId, Long memberId, Integer page);
}
