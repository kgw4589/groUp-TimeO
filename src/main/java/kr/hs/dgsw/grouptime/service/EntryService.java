package kr.hs.dgsw.grouptime.service;

import kr.hs.dgsw.grouptime.repository.EntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EntryService {
    private final EntryRepository entryRepository;

}
