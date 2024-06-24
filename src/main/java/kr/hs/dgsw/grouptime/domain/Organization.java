package kr.hs.dgsw.grouptime.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Organization extends BaseEntity {
    @Id
    private Long organizationId;

    private String name;

    private String email;

    @OneToMany(mappedBy = "organization")
    private List<Affiliation> affiliationList;

    public void update(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
