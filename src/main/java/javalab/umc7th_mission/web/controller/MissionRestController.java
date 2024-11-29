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
import javalab.umc7th_mission.domain.Mission;
import javalab.umc7th_mission.domain.enums.MissionStatus;
import javalab.umc7th_mission.domain.mapping.MemberMission;
import javalab.umc7th_mission.service.MissionService.MissionCommandService;
import javalab.umc7th_mission.service.MissionService.MissionQueryService;
import javalab.umc7th_mission.validation.annotation.CheckPage;
import javalab.umc7th_mission.web.dto.MissionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static javalab.umc7th_mission.web.dto.Mission.MissionRequestDTO.*;
import static javalab.umc7th_mission.web.dto.Mission.MissionResponseDTO.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/missions")
public class MissionRestController {

    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

    @PostMapping
    public ApiResponse<JoinResultDTO> join(@RequestBody @Valid JoinDto request){
        Mission mission = missionCommandService.joinMission(request);
        return ApiResponse.onSuccess(MissionConverter.toJoinResultDTO(mission));
    }

    @PostMapping("/challenge")
    public ApiResponse<ChallengeResultDTO> challenge(@RequestBody @Valid ChallengeDto request){
        MemberMission memberMission = missionCommandService.challengeMission(request);
        return ApiResponse.onSuccess(MissionConverter.toChallengeResultDTO(memberMission));
    }

    @PatchMapping("/complete")
    @Operation(summary = "미션 완료", description = "내가 진행 중인 미션을 완료 상태로 변경합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER4001", description = "존재하지 않는 유저입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<CompleteResultDto> complete(@RequestBody @Valid CompleteDto request) {
        MemberMission memberMission = missionCommandService.completeMission(request);
        return ApiResponse.onSuccess(MissionConverter.toCompleteResultDto(memberMission));
    }

    @GetMapping
    @Operation(summary = "내가 진행 중인 미션 목록 조회", description = "내가 진행 중인 미션 목록을 조회하는 API")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE4001", description = "페이징 번호가 유효하지 않습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "memberId, 쿼리 스트링입니다."),
            @Parameter(name = "status", description = "status, 쿼리 스트링입니다."),
            @Parameter(name = "page", description = "page, 쿼리 스트링입니다.")
    })
    public ApiResponse<MemberMissionListDTO> getMemberMissionList(@RequestParam(name = "memberId") Long memberId,
                                                  @RequestParam(name = "status") MissionStatus status,
                                                  @CheckPage @RequestParam(name = "page") Integer page) {
        Page<MemberMission> missionList = missionQueryService.getMemberMissionList(memberId, status, page - 1);
        return ApiResponse.onSuccess(MissionConverter.toMemberMissionListDTO(missionList));
    }
}
