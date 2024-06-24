package kr.hs.dgsw.grouptime.controller;

import kr.hs.dgsw.grouptime.dto.BaseResponse;
import kr.hs.dgsw.grouptime.dto.CommentDTO;
import kr.hs.dgsw.grouptime.dto.ScheduleDTO;
import kr.hs.dgsw.grouptime.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @GetMapping("/{scheduleId}")
    public BaseResponse<ScheduleDTO> getSchedule(@PathVariable Long scheduleId) {
        return new BaseResponse<>(200, "일정 조회 성공", scheduleService.getSchedule(scheduleId));
    }

    @PostMapping("/{scheduleId}/comments")
    public BaseResponse<CommentDTO> addCommentToSchedule(@PathVariable Long scheduleId, @RequestBody CommentDTO commentDTO) {
        return new BaseResponse<>(200, "코멘트 추가 성공", scheduleService.addCommentToSchedule(scheduleId, commentDTO));
    }
}
