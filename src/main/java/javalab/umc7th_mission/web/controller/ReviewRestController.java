package javalab.umc7th_mission.web.controller;

import jakarta.validation.Valid;
import javalab.umc7th_mission.apiPayload.ApiResponse;
import javalab.umc7th_mission.converter.ReviewConverter;
import javalab.umc7th_mission.domain.Review;
import javalab.umc7th_mission.service.ReviewService.ReviewCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static javalab.umc7th_mission.web.dto.Review.ReviewRequestDTO.*;
import static javalab.umc7th_mission.web.dto.Review.ReviewResponseDTO.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewRestController {

    private final ReviewCommandService reviewCommandService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<JoinResultDTO> join(
            @RequestPart(name = "ReviewRequestDTO", required = false) @Valid JoinDto request, // JSON
            @RequestPart(name = "files", required = false) List<MultipartFile> files // 이미지 파일들
    ) {
        Review review = reviewCommandService.joinReview(request, files);
        return ApiResponse.onSuccess(ReviewConverter.toJoinResultDTO(review));
    }
}
