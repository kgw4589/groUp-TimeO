package kr.hs.dgsw.grouptime.repository;

import kr.hs.dgsw.grouptime.domain.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Long> {
}
