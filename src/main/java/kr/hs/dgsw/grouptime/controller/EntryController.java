package kr.hs.dgsw.grouptime.controller;

import kr.hs.dgsw.grouptime.dto.BaseResponse;
import kr.hs.dgsw.grouptime.dto.EntryDTO;
import kr.hs.dgsw.grouptime.service.EntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/entry")
public class EntryController {
    private final EntryService entryService;

    @PostMapping
    public BaseResponse<Long> create(@RequestBody EntryDTO entryDTO) {
        return new BaseResponse(200, "일정 참가 성공", entryService.createEntry(entryDTO));
    }

    @DeleteMapping("/{entryId}")
    public BaseResponse<Void> delete(@PathVariable Long entryId) {
        entryService.delete(entryId);
        return new BaseResponse(200, "일정 참가 삭제 성공");
    }
}
