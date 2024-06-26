package kr.hs.dgsw.grouptime.service;

import kr.hs.dgsw.grouptime.domain.*;
import kr.hs.dgsw.grouptime.dto.CommentDTO;
import kr.hs.dgsw.grouptime.dto.LoginDTO;
import kr.hs.dgsw.grouptime.dto.ScheduleDTO;
import kr.hs.dgsw.grouptime.handler.exception.GlobalException;
import kr.hs.dgsw.grouptime.mapper.ScheduleMapper;
import kr.hs.dgsw.grouptime.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;
    private final ScheduleMapper scheduleMapper;
    private final EntryRepository entryRepository;


    public ScheduleDTO getSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(GlobalException::scheduleNotFound);
        return scheduleMapper.entityToDto(schedule);
    }

    public List<ScheduleDTO> getScheduleList(
            String category,
            String location,
            Long organizationId,
            Long userId
    ) {
        if (
            userId == null && organizationId == null
        ) {
            throw GlobalException.badRequestQueries();
        }
        List<Schedule> scheduleList;
        List<Schedule> response = new ArrayList<>();

        if (userId != null && organizationId != null) {
            scheduleList = new ArrayList<>();
            User user = userRepository
                    .findById(userId)
                    .orElseThrow(GlobalException::userNotFound);
            Organization organization = organizationRepository
                    .findById(organizationId)
                    .orElseThrow(GlobalException::scheduleNotFound);

            organization.getScheduleList().forEach(schedule -> {
                schedule.getEntryList().forEach(entry -> {
                    if (entry.getUser().equals(user))
                        scheduleList.add(schedule);
                });
            });
        } else if (organizationId == null) {
            User user = userRepository
                    .findById(userId)
                    .orElseThrow(GlobalException::userNotFound);

            scheduleList = user.getEntryList()
                    .stream()
                    .map(Entry::getSchedule)
                    .toList();
        } else {
            Organization organization = organizationRepository
                    .findById(organizationId)
                    .orElseThrow(GlobalException::organizationNotFound);

            scheduleList = organization.getScheduleList();
        }

        scheduleList.forEach(schedule -> {
            if (category == null && category == null) {
                response.add(schedule);
            } else if (location == null) {
                if (schedule.getCategory().equals(category)) response.add(schedule);
            } else if (category == null) {
                if (schedule.getLocation().equals(location)) response.add(schedule);
            } else {
                if (schedule.getCategory().equals(category) && schedule.getLocation().equals(location)) response.add(schedule);
            }
        });

        return response
                .stream()
                .map(scheduleMapper::entityToDto)
                .toList();
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
            scheduleDTO.getDate()
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

    public void deleteComment(Long scheduleId, Long commentId) {
        Schedule schedule = scheduleRepository
                .findById(scheduleId)
                .orElseThrow(GlobalException::scheduleNotFound);

        Comment comment = commentRepository
                .findByCommentIdAndSchedule(commentId, schedule)
                .orElseThrow(GlobalException::commentNotFound);

        commentRepository.delete(comment);
    }
}
