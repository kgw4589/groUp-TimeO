package kr.hs.dgsw.grouptime.repository;

import kr.hs.dgsw.grouptime.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
