package javalab.umc7th_mission.validation.validator;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import javalab.umc7th_mission.apiPayload.code.status.ErrorStatus;
import javalab.umc7th_mission.apiPayload.exception.GeneralException;
import javalab.umc7th_mission.domain.Member;
import javalab.umc7th_mission.repository.MemberMissionRepository.MemberMissionRepository;
import javalab.umc7th_mission.repository.MemberRepository.MemberRepository;
import javalab.umc7th_mission.validation.annotation.ExistChallenge;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static javalab.umc7th_mission.web.dto.Mission.MissionRequestDTO.*;

@Component
@RequiredArgsConstructor
public class ChallengeExistValidator implements ConstraintValidator<ExistChallenge, ChallengeDto> {

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public boolean isValid(ChallengeDto dto, ConstraintValidatorContext context) {
        Member member = memberRepository.findByEmail(dto.getMemberEmail())
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        boolean isValid = !memberMissionRepository.existsByMemberIdAndMissionId(member.getId(), dto.getMissionId());

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.ALREADY_CHALLENGE.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
