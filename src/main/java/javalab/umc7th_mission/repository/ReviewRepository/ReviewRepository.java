package javalab.umc7th_mission.repository.ReviewRepository;

import javalab.umc7th_mission.domain.Member;
import javalab.umc7th_mission.domain.Restaurant;
import javalab.umc7th_mission.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {
    Page<Review> findAllByRestaurantAndMember(Restaurant restaurant, Member member, PageRequest pageRequest);
}
