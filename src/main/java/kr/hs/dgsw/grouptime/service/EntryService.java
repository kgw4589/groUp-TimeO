package kr.hs.dgsw.grouptime.service;

import kr.hs.dgsw.grouptime.domain.Entry;
import kr.hs.dgsw.grouptime.domain.Schedule;
import kr.hs.dgsw.grouptime.domain.User;
import kr.hs.dgsw.grouptime.dto.EntryDTO;
import kr.hs.dgsw.grouptime.handler.exception.GlobalException;
import kr.hs.dgsw.grouptime.repository.EntryRepository;
import kr.hs.dgsw.grouptime.repository.ScheduleRepository;
import kr.hs.dgsw.grouptime.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EntryService {
    private final EntryRepository entryRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    public Long createEntry(EntryDTO entryDTO) {
        User user = userRepository
                .findById(entryDTO.getUserId())
                .orElseThrow(GlobalException::userNotFound);
        Schedule schedule = scheduleRepository
                .findById(entryDTO.getScheduleId())
                .orElseThrow(GlobalException::scheduleNotFound);

        Entry entry = Entry.builder()
                .user(user)
                .schedule(schedule)
                .build();

        entryRepository.save(entry);

        return entry.getEntryId();
    }

    public void delete(Long entryId) {
        Entry entry = entryRepository
                .findById(entryId)
                .orElseThrow(GlobalException::entryNotFound);

        entryRepository.delete(entry);
    }
}
