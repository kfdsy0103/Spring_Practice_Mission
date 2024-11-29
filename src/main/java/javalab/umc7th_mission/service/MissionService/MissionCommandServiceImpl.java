package javalab.umc7th_mission.service.MissionService;

import javalab.umc7th_mission.converter.MissionConverter;
import javalab.umc7th_mission.domain.Member;
import javalab.umc7th_mission.domain.Mission;
import javalab.umc7th_mission.domain.Restaurant;
import javalab.umc7th_mission.domain.enums.MissionStatus;
import javalab.umc7th_mission.domain.mapping.MemberMission;
import javalab.umc7th_mission.repository.MemberMissionRepository.MemberMissionRepository;
import javalab.umc7th_mission.repository.MemberRepository.MemberRepository;
import javalab.umc7th_mission.repository.MissionRepository.MissionRepository;
import javalab.umc7th_mission.repository.RestaurantRepository.RestaurantRepository;
import javalab.umc7th_mission.web.dto.Mission.MissionRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    @Transactional
    public Mission joinMission(MissionRequestDTO.JoinDto request) {
        Mission newMission = MissionConverter.toMission(request);
        Restaurant restaurant = restaurantRepository.findById((request.getRestaurantId())).get();

        // 연관 관계 설정
        newMission.setRestaurant(restaurant);
        newMission.setRegion(restaurant.getRegion());

        return missionRepository.save(newMission);
    }

    @Override
    @Transactional
    public MemberMission challengeMission(MissionRequestDTO.ChallengeDto request) {
        MemberMission newMemberMission = MissionConverter.toMemberMission(request);
        Member member = memberRepository.findByEmail(request.getMemberEmail()).get();
        Mission mission = missionRepository.findById(request.getMissionId()).get();

        // 연관 관계 설정
        newMemberMission.setMember(member);
        newMemberMission.setMission(mission);

        return memberMissionRepository.save(newMemberMission);
    }

    @Override
    @Transactional
    public MemberMission completeMission(MissionRequestDTO.CompleteDto request) {
        MemberMission memberMission = memberMissionRepository.findById(request.getMemberMissionId()).get();
        memberMission.setStatus(MissionStatus.COMPLETE);

        return memberMission;
    }


}
