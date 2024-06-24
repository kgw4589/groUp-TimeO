package kr.hs.dgsw.grouptime.service;

import kr.hs.dgsw.grouptime.domain.Affiliation;
import kr.hs.dgsw.grouptime.domain.Organization;
import kr.hs.dgsw.grouptime.domain.User;
import kr.hs.dgsw.grouptime.dto.CreateOrganizationDTO;
import kr.hs.dgsw.grouptime.dto.OrganizationDTO;
import kr.hs.dgsw.grouptime.mapper.AffiliationMapper;
import kr.hs.dgsw.grouptime.mapper.OrganizationMapper;
import kr.hs.dgsw.grouptime.repository.AffiliationRepository;
import kr.hs.dgsw.grouptime.repository.OrganizationRepository;
import kr.hs.dgsw.grouptime.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final UserRepository userRepository;
    private final AffiliationRepository affiliationRepository;
    private final OrganizationMapper organizationMapper;

    public Long createOrganization(CreateOrganizationDTO createOrganizationDTO) {
        Optional<User> user = userRepository.findById(createOrganizationDTO.getUserId());

        if (user.isPresent()) {
            Organization organization = organizationMapper.dtoToEntity(createOrganizationDTO.getOrganization());

            organizationRepository.save(organization);

            Affiliation affiliation = Affiliation.builder()
                    .user(user.get())
                    .organization(organization)
                    .build();

            affiliationRepository.save(affiliation);

            return organization.getGroupId();
        }

        return null;
    }

    public OrganizationDTO getOrganization(Long organizationId) {
        Optional<Organization> organization = organizationRepository.findById(organizationId);

        return organization.isPresent() ? organizationMapper.entityToDto(organization.get()) : null;
    }

    public void update(OrganizationDTO organizationDTO) {
        Optional<Organization> organization = organizationRepository.findById(organizationDTO.getGroupId());

        if (organization.isPresent()) {

        }
    }
}
