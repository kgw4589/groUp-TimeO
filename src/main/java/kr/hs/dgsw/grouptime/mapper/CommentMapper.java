package kr.hs.dgsw.grouptime.mapper;

import kr.hs.dgsw.grouptime.domain.Comment;
import kr.hs.dgsw.grouptime.dto.CommentDTO;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public CommentDTO entityToDto(Comment comment) {
        return CommentDTO.builder()
                .commentId(comment.getCommentId())
                .text(comment.getText())
                .schedule(comment.getSchedule())
                .regDate(comment.getRegDate())
                .modDate(comment.getModDate())
                .build();
    }

    public Comment dtoToEntity(CommentDTO commentDTO) {
        return Comment.builder()
                .commentId(commentDTO.getCommentId())
                .schedule(commentDTO.getSchedule())
                .text(commentDTO.getText())
                .build();
    }
}
