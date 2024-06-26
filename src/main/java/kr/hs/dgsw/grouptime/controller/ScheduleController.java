package kr.hs.dgsw.grouptime.controller;

import jakarta.persistence.Id;
import kr.hs.dgsw.grouptime.dto.BaseResponse;
import kr.hs.dgsw.grouptime.dto.CommentDTO;
import kr.hs.dgsw.grouptime.dto.ScheduleDTO;
import kr.hs.dgsw.grouptime.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @GetMapping("/{scheduleId}")
    public BaseResponse<ScheduleDTO> getSchedule(@PathVariable Long scheduleId) {
        return new BaseResponse(200, "일정 조회 성공", scheduleService.getSchedule(scheduleId));
    }

    @GetMapping("/list")
    public BaseResponse<List<ScheduleDTO>> getScheduleList(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Long organizationId,
            @RequestParam(required = false) Long userId
    ) {
        return new BaseResponse(
            200,
            "일정 리스트 조회 성공",
            scheduleService.getScheduleList(category, location, organizationId, userId)
        );
    }

    @PostMapping
    public BaseResponse<String> postSchedule(@RequestBody ScheduleDTO scheduleDTO){
        return new BaseResponse(200, "일정 생성 완료", scheduleService.createSchedule(scheduleDTO));
    }

    @PutMapping
    public BaseResponse<String> updateSchedule(@RequestBody ScheduleDTO scheduleDTO){
        scheduleService.modifySchedule(scheduleDTO);
        return new BaseResponse(200, "일정 수정 완료");
    }

    @DeleteMapping("/{scheduleId}")
    public BaseResponse<String> deleteSchedule(@PathVariable Long scheduleId){
        scheduleService.delete(scheduleId);
        return new BaseResponse(200, "삭제완료");
    }

    //일정 조회
    //일정 생성
    //일정 삭제
    //일정 추가

    @PostMapping("/{scheduleId}/comment")
    public BaseResponse<Long> addCommentToSchedule(@PathVariable Long scheduleId, @RequestBody CommentDTO commentDTO) {
        return new BaseResponse(200, "코멘트 추가 성공", scheduleService.addCommentToSchedule(scheduleId, commentDTO));
    }

    @DeleteMapping("/{scheduleId}/comment/{commentId}")
    public BaseResponse<Void> deleteComment(@PathVariable Long scheduleId, @PathVariable Long commentId) {
        scheduleService.delete(scheduleId);
        return new BaseResponse(200, "코멘트 삭제 성공");
    }
}
