package javalab.umc7th_mission.service.ReviewService;

import javalab.umc7th_mission.domain.Member;
import javalab.umc7th_mission.domain.Restaurant;
import javalab.umc7th_mission.domain.Review;
import javalab.umc7th_mission.repository.MemberRepository.MemberRepository;
import javalab.umc7th_mission.repository.RestaurantRepository.RestaurantRepository;
import javalab.umc7th_mission.repository.ReviewRepository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final RestaurantRepository restaurantRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public Page<Review> getMemberReviewList(Long restaurantId, Long memberId, Integer page) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
        Member member = memberRepository.findById(memberId).get();

        return reviewRepository.findAllByRestaurantAndMember(restaurant, member, PageRequest.of(page, 10));
    }
}
