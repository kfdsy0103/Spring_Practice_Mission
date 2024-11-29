package javalab.umc7th_mission.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import javalab.umc7th_mission.apiPayload.ApiResponse;
import javalab.umc7th_mission.converter.MissionConverter;
import javalab.umc7th_mission.converter.RestaurantConverter;
import javalab.umc7th_mission.converter.ReviewConverter;
import javalab.umc7th_mission.domain.Mission;
import javalab.umc7th_mission.domain.Restaurant;
import javalab.umc7th_mission.domain.Review;
import javalab.umc7th_mission.service.MissionService.MissionQueryService;
import javalab.umc7th_mission.service.RestaurantService.RestaurantCommandService;

import javalab.umc7th_mission.service.ReviewService.ReviewQueryService;
import javalab.umc7th_mission.validation.annotation.CheckPage;
import javalab.umc7th_mission.validation.annotation.ExistRestaurant;
import javalab.umc7th_mission.web.dto.Mission.MissionResponseDTO;
import javalab.umc7th_mission.web.dto.Review.ReviewResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static javalab.umc7th_mission.web.dto.Restaurant.RestaurantRequestDTO.*;
import static javalab.umc7th_mission.web.dto.Restaurant.RestaurantResponseDTO.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/restaurants")
public class RestaurantRestController {

    private final RestaurantCommandService restaurantCommandService;
    private final ReviewQueryService reviewQueryService;
    private final MissionQueryService missionQueryService;

    @PostMapping
    public ApiResponse<JoinResultDTO> join(@RequestBody @Valid JoinDto request){
        Restaurant restaurant = restaurantCommandService.joinRestaurant(request);
        return ApiResponse.onSuccess(RestaurantConverter.toJoinResultDTO(restaurant));
    }

    @GetMapping("/{restaurantId}/reviews")
    @Operation(summary = "내가 작성한 리뷰 목록 조회", description = "특정 가게에서 내가 작성한 리뷰 목록을 조회하는 API")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "RESTAURANT4001", description = "식당이 존재하지 않습니다." ,content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE4001", description = "페이징 번호가 유효하지 않습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "restaurantId", description = "restaurantId, path variable 입니다."),
            @Parameter(name = "memberId", description = "memberId, 쿼리 스트링입니다."),
            @Parameter(name = "page", description = "page, 쿼리 스트링입니다.")
    })
    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDTO> getReviewList(@ExistRestaurant @PathVariable(name = "restaurantId") Long restaurantId,
                                                                             @RequestParam(name = "memberId") Long memberId,
                                                                             @CheckPage @RequestParam(name = "page") Integer page) {
        Page<Review> reviewList = reviewQueryService.getMemberReviewList(restaurantId, memberId, page - 1);
        return ApiResponse.onSuccess(ReviewConverter.reviewPreViewListDTO(reviewList));
    }

    @GetMapping("/{restaurantId}/missions")
    @Operation(summary = "수행 가능한 미션 목록 조회", description = "특정 가게에서 수행 가능한 미션 목록을 조회하는 API")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "RESTAURANT4001", description = "식당이 존재하지 않습니다." ,content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE4001", description = "페이징 번호가 유효하지 않습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "restaurantId", description = "restaurantId, path variable 입니다."),
            @Parameter(name = "memberId", description = "memberId, 쿼리 스트링입니다."),
            @Parameter(name = "page", description = "page, 쿼리 스트링입니다.")
    })
    public ApiResponse<MissionResponseDTO.MissionPreViewListDTO> getMissionList(@ExistRestaurant @PathVariable(name = "restaurantId") Long restaurantId,
                                                          @RequestParam(name = "memberId") Long memberId,
                                                          @CheckPage @RequestParam(name = "page") Integer page) {
        Page<Mission> missionList = missionQueryService.getRestaurantMissionList(restaurantId, memberId, page - 1);
        return ApiResponse.onSuccess(MissionConverter.toMissionPreviewListDTO(missionList));
    }

}
