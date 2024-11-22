package javalab.umc7th_mission.service.ReviewService;

import javalab.umc7th_mission.converter.ReviewConverter;
import javalab.umc7th_mission.domain.*;
import javalab.umc7th_mission.repository.MemberRepository.MemberRepository;
import javalab.umc7th_mission.repository.RestaurantRepository.RestaurantRepository;
import javalab.umc7th_mission.repository.ReviewRepository.ReviewRepository;
import javalab.umc7th_mission.service.FileService.FileService;
import javalab.umc7th_mission.web.dto.Review.ReviewRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final MemberRepository memberRepository;
    private final FileService fileService;

    @Override
    @Transactional
    public Review joinReview(ReviewRequestDTO.JoinDto request, List<MultipartFile> images) {
        Review newReview = ReviewConverter.toReview(request);
        Member member = memberRepository.findByEmail(request.getMemberEmail()).get();
        Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId()).get();

        // 1. fileService에 이미지 저장 후 이미지 url 반환
        List<String> imageUrls = images.stream()
                .map(image -> {
                    return fileService.saveFile(image);
                }).toList();

        // 2. url로 ReviewImage 엔티티 생성
        List<ReviewImage> reviewImages = imageUrls.stream().map(url -> {
            return ReviewImage.builder()
                    .url(url)
                    .build();
        }).toList();
        
        // 3. 연관 관계 설정 (reviewImage는 cacadeALL로 자동 저장)
        reviewImages
                .forEach(reviewImage -> newReview.addImage(reviewImage));

        return reviewRepository.save(newReview);
    }
}
