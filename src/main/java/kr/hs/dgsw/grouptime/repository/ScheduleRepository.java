package kr.hs.dgsw.grouptime.repository;

import kr.hs.dgsw.grouptime.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
