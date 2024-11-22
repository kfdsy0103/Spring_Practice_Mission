package javalab.umc7th_mission.converter;

import javalab.umc7th_mission.domain.*;
import javalab.umc7th_mission.web.dto.Review.ReviewRequestDTO;
import javalab.umc7th_mission.web.dto.Review.ReviewResponseDTO;

public class ReviewConverter {

    public static ReviewResponseDTO.JoinResultDTO toJoinResultDTO(Review review){
        return ReviewResponseDTO.JoinResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static Review toReview(ReviewRequestDTO.JoinDto request){
        return Review.builder()
                .content(request.getContent())
                .score(request.getScore())
                .build();
    }
}
