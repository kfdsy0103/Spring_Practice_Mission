package javalab.umc7th_mission.converter;

import javalab.umc7th_mission.domain.*;
import javalab.umc7th_mission.web.dto.Review.ReviewRequestDTO;
import javalab.umc7th_mission.web.dto.Review.ReviewResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

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

    public static ReviewResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){

        List<String> imageUrl = review.getReviewImageList().stream()
                .map(reviewImage -> reviewImage.getUrl())
                .toList();

        return ReviewResponseDTO.ReviewPreViewDTO.builder()
                .reviewer(review.getMember().getName())
                .score(review.getScore())
                .content(review.getContent())
                .imageUrl(imageUrl)
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){
        List<ReviewResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(review -> reviewPreViewDTO(review))
                .toList();

        return ReviewResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }
}
