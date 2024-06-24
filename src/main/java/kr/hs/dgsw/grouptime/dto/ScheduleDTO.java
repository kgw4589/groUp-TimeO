package kr.hs.dgsw.grouptime.dto;

import lombok.*;

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
    private String date;
    private List<CommentDTO> comments;
}
