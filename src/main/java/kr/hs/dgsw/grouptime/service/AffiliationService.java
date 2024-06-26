package kr.hs.dgsw.grouptime.service;

import kr.hs.dgsw.grouptime.domain.Affiliation;
import kr.hs.dgsw.grouptime.domain.Organization;
import kr.hs.dgsw.grouptime.domain.User;
import kr.hs.dgsw.grouptime.handler.exception.GlobalException;
import kr.hs.dgsw.grouptime.repository.AffiliationRepository;
import kr.hs.dgsw.grouptime.repository.OrganizationRepository;
import kr.hs.dgsw.grouptime.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.NonUniqueResultException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AffiliationService {
    private final AffiliationRepository affiliationRepository;
    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;

    public void createAffiliation(Long userId, Long organizationId) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(GlobalException::userNotFound);
        Organization organization = organizationRepository
                .findById(organizationId)
                .orElseThrow(GlobalException::organizationNotFound);

        if (affiliationRepository.findByUserAndOrganization(user, organization).isPresent()) {
            throw new NonUniqueResultException(2);
        }

        Affiliation affiliation = Affiliation.builder()
                .organization(organization)
                .user(user)
                .build();

        affiliationRepository.save(affiliation);
    }

    public void delete(Long userId, Long organizationId) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(GlobalException::userNotFound);

        Organization organization = organizationRepository
                .findById(organizationId)
                .orElseThrow(GlobalException::organizationNotFound);

        Affiliation affiliation = affiliationRepository
                .findByUserAndOrganization(user, organization)
                .orElseThrow(GlobalException::affiliationNotFound);

        affiliationRepository.delete(affiliation);
    }
}
