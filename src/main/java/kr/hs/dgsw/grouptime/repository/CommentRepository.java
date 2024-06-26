package kr.hs.dgsw.grouptime.repository;

import kr.hs.dgsw.grouptime.domain.Comment;
import kr.hs.dgsw.grouptime.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByCommentIdAndSchedule(Long commentId, Schedule schedule);
}
