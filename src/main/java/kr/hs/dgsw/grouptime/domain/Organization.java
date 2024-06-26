package kr.hs.dgsw.grouptime.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Organization extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long organizationId;

    private String name;

    private String email;

    @OneToMany(mappedBy = "organization")
    private List<Affiliation> affiliationList;

    @OneToMany(mappedBy = "organization")
    private List<Schedule> scheduleList;

    public void update(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
