package javalab.umc7th_mission.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import javalab.umc7th_mission.domain.common.BaseEntity;
import javalab.umc7th_mission.domain.enums.Gender;
import javalab.umc7th_mission.domain.enums.MemberStatus;
import javalab.umc7th_mission.domain.enums.SocialType;
import javalab.umc7th_mission.domain.mapping.MemberFood;
import javalab.umc7th_mission.domain.mapping.MemberTerm;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@DynamicInsert
@DynamicUpdate
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull
    @Size(max = 30)
    private String email;

    @NotNull
    @Size(max = 20)
    private String name;

    @NotNull
    private LocalDate birth;

    @NotNull
    @Size(max = 50)
    private String address;

    private Integer point;

    @Enumerated(EnumType.STRING)
    @Size(max = 10)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Size(max = 10)
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    @Size(max = 10)
    @ColumnDefault("'ACTIVE'")
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
