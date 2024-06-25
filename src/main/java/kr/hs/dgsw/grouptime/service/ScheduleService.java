package kr.hs.dgsw.grouptime.service;

import kr.hs.dgsw.grouptime.domain.*;
import kr.hs.dgsw.grouptime.dto.CommentDTO;
import kr.hs.dgsw.grouptime.dto.LoginDTO;
import kr.hs.dgsw.grouptime.dto.ScheduleDTO;
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
        Schedule schedule = scheduleRepository.findById(id).orElseThrow();
        return scheduleMapper.entityToDto(schedule);
    }



    public String createSchedule(ScheduleDTO scheduleDTO) {
         Schedule scheduleData =  scheduleMapper.dtoToEntity(scheduleDTO);
         scheduleRepository.save(scheduleData);
         return "생성성공";
    }

    public String modifySchedule(ScheduleDTO scheduleDTO){
        Optional<Schedule> scheduleData =  scheduleRepository.findById(scheduleDTO.getScheduleId());
        if (scheduleData.isPresent()){
            Schedule scheduleEntity = scheduleData.get();
            scheduleEntity.update(
                    scheduleDTO.getTitle(),
                    scheduleDTO.getDescription(),
                    scheduleDTO.getLocation(),
                    scheduleDTO.getCategory(),
                    scheduleDTO.getDate(),
                    scheduleDTO.getComments()
            );

            scheduleRepository.save(scheduleEntity);
        }
        return "일정 수정 완료";
    }

    public String delete(Long scheduleId){
        Optional<Schedule> scheduleData =  scheduleRepository.findById(scheduleId);
        if (scheduleData.isPresent()){
            scheduleRepository.delete(scheduleData.get());
        }
        return "삭제 완료";
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
