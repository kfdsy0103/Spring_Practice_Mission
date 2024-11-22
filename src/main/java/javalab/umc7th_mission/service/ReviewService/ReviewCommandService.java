package javalab.umc7th_mission.service.ReviewService;

import javalab.umc7th_mission.domain.Review;
import javalab.umc7th_mission.web.dto.Review.ReviewRequestDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReviewCommandService {
    Review joinReview(ReviewRequestDTO.JoinDto request, List<MultipartFile> images);
}
