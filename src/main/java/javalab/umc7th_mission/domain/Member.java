package javalab.umc7th_mission.domain;

import jakarta.persistence.*;
import javalab.umc7th_mission.domain.common.BaseEntity;
import javalab.umc7th_mission.domain.enums.Gender;
import javalab.umc7th_mission.domain.enums.MemberStatus;
import javalab.umc7th_mission.domain.enums.SocialType;
import javalab.umc7th_mission.domain.mapping.MemberFood;
import javalab.umc7th_mission.domain.mapping.MemberTerm;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false, unique = true)
    private String email;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate birth;

    @Column(nullable = false, length = 50)
    private String address;

    private Integer point;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10) DEFAULT 'ACTIVE'")
    private MemberStatus status;

    private LocalDate inactiveDate;

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<MemberTerm> memberTermList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Alarm> alarmList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<MemberFood> memberFoodList = new ArrayList<>();
}
