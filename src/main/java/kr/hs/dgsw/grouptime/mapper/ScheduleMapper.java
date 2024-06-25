package kr.hs.dgsw.grouptime.mapper;

import kr.hs.dgsw.grouptime.domain.Schedule;
import kr.hs.dgsw.grouptime.dto.ScheduleDTO;
import kr.hs.dgsw.grouptime.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ScheduleMapper {
    private final UserMapper userMapper;

    public ScheduleDTO entityToDto(Schedule schedule) {
        List<UserDTO> userList = schedule
                .getEntryList()
                .stream()
                .map(entry ->  {
                    return userMapper.entityToDto(entry.getUser());
                })
                .toList();

        return ScheduleDTO.builder()
                .scheduleId(schedule.getScheduleId())
                .title(schedule.getTitle())
                .description(schedule.getDescription())
                .location(schedule.getLocation())
                .date(schedule.getDate())
                .category(schedule.getCategory())
                .comments(schedule.getComments())
                .entryList(userList)
                .build();
    }

    public Schedule dtoToEntity(ScheduleDTO scheduleDTO) {
        return Schedule.builder()
                .scheduleId(scheduleDTO.getScheduleId())
                .title(scheduleDTO.getTitle())
                .description(scheduleDTO.getDescription())
                .location(scheduleDTO.getLocation())
                .date(scheduleDTO.getDate())
                .comments(scheduleDTO.getComments())
                .build();
    }
}
