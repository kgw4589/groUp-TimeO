package kr.hs.dgsw.grouptime.dto;

import kr.hs.dgsw.grouptime.domain.Schedule;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentDTO {
    private Long commentId;
    private String text;
    private Schedule schedule;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
