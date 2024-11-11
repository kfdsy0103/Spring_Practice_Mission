package javalab.umc7th_mission.repository.ReviewRepository;

import javalab.umc7th_mission.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {
}
