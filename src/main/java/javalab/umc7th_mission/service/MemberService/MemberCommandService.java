package javalab.umc7th_mission.service.MemberService;

import javalab.umc7th_mission.domain.Member;
import javalab.umc7th_mission.web.dto.Member.MemberRequestDTO;

public interface MemberCommandService {
    Member joinMember(MemberRequestDTO.JoinDto request);
}
