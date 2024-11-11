package javalab.umc7th_mission.domain.mapping;

import jakarta.persistence.*;
import javalab.umc7th_mission.domain.Member;
import javalab.umc7th_mission.domain.Term;
import javalab.umc7th_mission.domain.common.BaseEntity;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberTerm extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "term_id")
    private Term term;

    private boolean agree;
}