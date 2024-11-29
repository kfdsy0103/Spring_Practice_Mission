package javalab.umc7th_mission.converter;

import javalab.umc7th_mission.domain.Mission;
import javalab.umc7th_mission.domain.mapping.MemberMission;
import javalab.umc7th_mission.web.dto.Mission.MissionRequestDTO;
import javalab.umc7th_mission.web.dto.Mission.MissionResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public class MissionConverter {

    public static MissionResponseDTO.JoinResultDTO toJoinResultDTO(Mission mission){
        return MissionResponseDTO.JoinResultDTO.builder()
                .missionId(mission.getId())
                .createdAt(mission.getCreatedAt())
                .build();
    }

    public static Mission toMission(MissionRequestDTO.JoinDto request){
        return Mission.builder()
                .content(request.getContent())
                .point(request.getPoint())
                .deadline(request.getDeadline())
                .build();
    }

    public static MissionResponseDTO.ChallengeResultDTO toChallengeResultDTO(MemberMission memberMission) {
        return MissionResponseDTO.ChallengeResultDTO.builder()
                .memberMissionId(memberMission.getId())
                .createdAt(memberMission.getCreatedAt())
                .build();
    }

    public static MemberMission toMemberMission(MissionRequestDTO.ChallengeDto request) {
        return MemberMission.builder()
                .status(request.getStatus())
                .build();
    }

    public static MissionResponseDTO.MissionPreViewDTO toMissionPreviewDTO(Mission mission) {
        return MissionResponseDTO.MissionPreViewDTO.builder()
                .missionId(mission.getId())
                .content(mission.getContent())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .build();
    }

    public static MissionResponseDTO.MissionPreViewListDTO toMissionPreviewListDTO(Page<Mission> missionList) {
        List<MissionResponseDTO.MissionPreViewDTO> missionPreViewDTOList = missionList.stream()
                .map(mission -> toMissionPreviewDTO(mission))
                .toList();

        return MissionResponseDTO.MissionPreViewListDTO.builder()
                .missionList(missionPreViewDTOList)
                .listSize(missionPreViewDTOList.size())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .isFirst(missionList.isFirst())
                .isLast(missionList.isLast())
                .build();
    }

    public static MissionResponseDTO.MemberMissionDTO toMemberMissionDTO(MemberMission mission) {
        return MissionResponseDTO.MemberMissionDTO.builder()
                .missionId(mission.getMission().getId())
                .restaurantId(mission.getId())
                .content(mission.getMission().getContent())
                .point(mission.getMission().getPoint())
                .deadline(mission.getMission().getDeadline())
                .build();
    }

    public static MissionResponseDTO.MemberMissionListDTO toMemberMissionListDTO(Page<MemberMission> missionList) {
        List<MissionResponseDTO.MemberMissionDTO> memberMissionDTOList = missionList.stream()
                .map(memberMission -> toMemberMissionDTO(memberMission))
                .toList();

        return MissionResponseDTO.MemberMissionListDTO.builder()
                .missionList(memberMissionDTOList)
                .listSize(memberMissionDTOList.size())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .isFirst(missionList.isFirst())
                .isLast(missionList.isLast())
                .build();
    }

    public static MissionResponseDTO.CompleteResultDto toCompleteResultDto(MemberMission request) {
        return MissionResponseDTO.CompleteResultDto.builder()
                .memberMissionId(request.getId())
                .status(request.getStatus())
                .build();
    }
}
