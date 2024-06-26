package kr.hs.dgsw.grouptime.repository;

import kr.hs.dgsw.grouptime.domain.Entry;
import kr.hs.dgsw.grouptime.domain.Schedule;
import kr.hs.dgsw.grouptime.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EntryRepository extends JpaRepository<Entry, Long> {
    Optional<Entry> findByUserAndSchedule(User user, Schedule schedule);
}
