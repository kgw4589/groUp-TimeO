package kr.hs.dgsw.grouptime.dto;

import kr.hs.dgsw.grouptime.domain.Comment;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ScheduleDTO {
    private Long scheduleId;
    private String title;
    private String description;
    private String location;
    private LocalDate date;
    private String category;
    private List<UserDTO> entryList;
    private List<Comment> comments;
}
