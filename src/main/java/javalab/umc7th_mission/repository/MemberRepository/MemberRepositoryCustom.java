package javalab.umc7th_mission.repository.MemberRepository;

import javalab.umc7th_mission.web.dto.MyPageDTO;

public interface MemberRepositoryCustom {
    MyPageDTO findMyPageById(Long id);
}
