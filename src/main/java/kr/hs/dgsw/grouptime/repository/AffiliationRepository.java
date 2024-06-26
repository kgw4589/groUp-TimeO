package kr.hs.dgsw.grouptime.repository;

import kr.hs.dgsw.grouptime.domain.Affiliation;
import kr.hs.dgsw.grouptime.domain.Organization;
import kr.hs.dgsw.grouptime.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AffiliationRepository extends JpaRepository<Affiliation, Long> {
    Optional<Affiliation> findByUserAndOrganization(User user, Organization organization);
}
