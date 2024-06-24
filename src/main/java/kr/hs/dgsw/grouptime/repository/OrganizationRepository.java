package kr.hs.dgsw.grouptime.repository;

import kr.hs.dgsw.grouptime.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
