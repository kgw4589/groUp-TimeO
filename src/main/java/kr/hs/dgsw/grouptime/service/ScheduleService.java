package kr.hs.dgsw.grouptime.service;

import kr.hs.dgsw.grouptime.domain.*;
import kr.hs.dgsw.grouptime.dto.CommentDTO;
import kr.hs.dgsw.grouptime.dto.LoginDTO;
import kr.hs.dgsw.grouptime.dto.ScheduleDTO;
import kr.hs.dgsw.grouptime.handler.exception.GlobalException;
import kr.hs.dgsw.grouptime.mapper.ScheduleMapper;
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
    private final ScheduleMapper scheduleMapper;


    public ScheduleDTO getSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(GlobalException::scheduleNotFound);
        return scheduleMapper.entityToDto(schedule);
    }



    public Long createSchedule(ScheduleDTO scheduleDTO) {
         Schedule schedule =  scheduleMapper.dtoToEntity(scheduleDTO);
         scheduleRepository.save(schedule);
         return schedule.getScheduleId();
    }

    public void modifySchedule(ScheduleDTO scheduleDTO){
        Schedule schedule =  scheduleRepository
                .findById(scheduleDTO.getScheduleId())
                .orElseThrow(GlobalException::scheduleNotFound);

        schedule.update(
            scheduleDTO.getTitle(),
            scheduleDTO.getDescription(),
            scheduleDTO.getLocation(),
            scheduleDTO.getCategory(),
            scheduleDTO.getDate(),
            scheduleDTO.getComments()
        );

        scheduleRepository.save(schedule);
    }

    public void delete(Long scheduleId){
        Schedule schedule =  scheduleRepository
                .findById(scheduleId)
                .orElseThrow(GlobalException::scheduleNotFound);

        scheduleRepository.delete(schedule);
    }





    public Long addCommentToSchedule(Long scheduleId, CommentDTO commentDTO) {
        Schedule schedule = scheduleRepository
                .findById(scheduleId)
                .orElseThrow(GlobalException::scheduleNotFound);

        Comment comment = Comment.builder()
                .text(commentDTO.getText())
                .schedule(schedule)
                .build();

        commentRepository.save(comment);

        return comment.getCommentId();
    }
}
