package kr.hs.dgsw.grouptime.domain;

import jakarta.persistence.*;
import kr.hs.dgsw.grouptime.dto.UserDTO;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @OneToMany(mappedBy = "schedule")
    private List<Entry> entryList;

    @OneToMany(mappedBy = "schedule")
    private List<Comment> comments;

    public void update(String title, String description, String location, String category, LocalDate date) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.category = category;
        this.date = date;
    }
}