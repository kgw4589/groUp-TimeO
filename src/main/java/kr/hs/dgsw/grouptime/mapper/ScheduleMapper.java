package kr.hs.dgsw.grouptime.mapper;

import kr.hs.dgsw.grouptime.domain.Organization;
import kr.hs.dgsw.grouptime.domain.Schedule;
import kr.hs.dgsw.grouptime.dto.CommentDTO;
import kr.hs.dgsw.grouptime.dto.ScheduleDTO;
import kr.hs.dgsw.grouptime.dto.UserDTO;
import kr.hs.dgsw.grouptime.handler.exception.GlobalException;
import kr.hs.dgsw.grouptime.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ScheduleMapper {
    private final UserMapper userMapper;
    private final CommentMapper commentMapper;
    private final OrganizationRepository organizationRepository;

    public ScheduleDTO entityToDto(Schedule schedule) {
        List<UserDTO> userList = schedule
                .getEntryList()
                .stream()
                .map(entry ->  {
                    return userMapper.entityToDto(entry.getUser());
                })
                .toList();

        List<CommentDTO> commentList = schedule
                .getComments()
                .stream()
                .map(comment -> {
                    return commentMapper.entityToDto(comment);
                })
                .toList();

        return ScheduleDTO.builder()
                .scheduleId(schedule.getScheduleId())
                .title(schedule.getTitle())
                .description(schedule.getDescription())
                .location(schedule.getLocation())
                .date(schedule.getDate())
                .category(schedule.getCategory())
                .comments(commentList)
                .entryList(userList)
                .organizationId(schedule.getOrganization().getOrganizationId())
                .build();
    }

    public Schedule dtoToEntity(ScheduleDTO scheduleDTO) {
        Organization organization = organizationRepository.findById(scheduleDTO.getOrganizationId()).orElseThrow(GlobalException::scheduleNotFound);
        return Schedule.builder()
                .scheduleId(scheduleDTO.getScheduleId())
                .title(scheduleDTO.getTitle())
                .description(scheduleDTO.getDescription())
                .location(scheduleDTO.getLocation())
                .date(scheduleDTO.getDate())
                .organization(organization)
                .category(scheduleDTO.getCategory())
                .build();
    }
}
