package kr.hs.dgsw.grouptime.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private String text;

//    @ManyToOne
//    @JoinColumn(name = "schedule_id")
//    private Schedule schedule;

    public void update(String text, String email, String password) {
        this.text = text;
    }
}
