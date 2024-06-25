package kr.hs.dgsw.grouptime.service;

import kr.hs.dgsw.grouptime.domain.Affiliation;
import kr.hs.dgsw.grouptime.domain.Organization;
import kr.hs.dgsw.grouptime.domain.User;
import kr.hs.dgsw.grouptime.repository.AffiliationRepository;
import kr.hs.dgsw.grouptime.repository.OrganizationRepository;
import kr.hs.dgsw.grouptime.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AffiliationService {
    private final AffiliationRepository affiliationRepository;
    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;

    public void createAffiliation(Long userId, Long organizationId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Organization> organization = organizationRepository.findById(organizationId);

        if (user.isPresent() && organization.isPresent()) {
            Affiliation affiliation = Affiliation.builder()
                    .organization(organization.get())
                    .user(user.get())
                    .build();

            affiliationRepository.save(affiliation);
        }
    }

    public void delete(Long affiliationId) {
        Optional<Affiliation> affiliation = affiliationRepository.findById(affiliationId);

        if (affiliation.isPresent()) {
            affiliationRepository.delete(affiliation.get());
        }
    }
}
