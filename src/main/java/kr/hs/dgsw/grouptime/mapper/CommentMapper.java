package kr.hs.dgsw.grouptime.mapper;

import kr.hs.dgsw.grouptime.domain.Comment;
import kr.hs.dgsw.grouptime.domain.Schedule;
import kr.hs.dgsw.grouptime.dto.CommentDTO;
import kr.hs.dgsw.grouptime.dto.ScheduleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentMapper {

    public CommentDTO entityToDto(Comment comment) {
        return CommentDTO.builder()
                .commentId(comment.getCommentId())
                .text(comment.getText())
                .regDate(comment.getRegDate())
                .modDate(comment.getModDate())
                .build();
    }

    public Comment dtoToEntity(CommentDTO commentDTO) {
        return Comment.builder()
                .commentId(commentDTO.getCommentId())
                .text(commentDTO.getText())
                .build();
    }
}
