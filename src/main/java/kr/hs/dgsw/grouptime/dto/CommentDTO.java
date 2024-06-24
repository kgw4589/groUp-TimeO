package kr.hs.dgsw.grouptime.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentDTO {
    private Long commentId;
    private String text;
    private Long scheduleId;
}
