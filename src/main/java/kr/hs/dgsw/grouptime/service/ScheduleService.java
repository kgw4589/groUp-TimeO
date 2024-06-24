package kr.hs.dgsw.grouptime.service;

import kr.hs.dgsw.grouptime.domain.Comment;
import kr.hs.dgsw.grouptime.domain.Schedule;
import kr.hs.dgsw.grouptime.dto.CommentDTO;
import kr.hs.dgsw.grouptime.dto.ScheduleDTO;
import kr.hs.dgsw.grouptime.repository.CommentRepository;
import kr.hs.dgsw.grouptime.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;

    public ScheduleDTO getSchedule(Long id) {
        Optional<Schedule> scheduleOpt = scheduleRepository.findById(id);
        if (scheduleOpt.isPresent()) {
            Schedule schedule = scheduleOpt.get();
            return ScheduleDTO.builder()
                    .scheduleId(schedule.getScheduleId())
                    .title(schedule.getTitle())
                    .description(schedule.getDescription())
                    .date(schedule.getDate())
                    .comments(schedule.getComments().stream()
                            .map(comment -> CommentDTO.builder()
                                    .commentId(comment.getCommentId())
                                    .text(comment.getText())
                                    .scheduleId(comment.getSchedule().getScheduleId())
                                    .build())
                            .collect(Collectors.toList()))
                    .build();
        }
        return null;
    }

    public CommentDTO addCommentToSchedule(Long scheduleId, CommentDTO commentDTO) {
        Optional<Schedule> scheduleOpt = scheduleRepository.findById(scheduleId);
        if (scheduleOpt.isPresent()) {
            Schedule schedule = scheduleOpt.get();
            Comment comment = Comment.builder()
                    .text(commentDTO.getText())
                    .schedule(schedule)
                    .build();
            commentRepository.save(comment);

            commentDTO.setCommentId(comment.getCommentId());
            commentDTO.setScheduleId(schedule.getScheduleId());
            return commentDTO;
        }
        return null;
    }
}
