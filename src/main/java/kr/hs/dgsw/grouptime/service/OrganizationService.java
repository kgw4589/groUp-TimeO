package kr.hs.dgsw.grouptime.service;

import kr.hs.dgsw.grouptime.domain.Affiliation;
import kr.hs.dgsw.grouptime.domain.Organization;
import kr.hs.dgsw.grouptime.domain.User;
import kr.hs.dgsw.grouptime.dto.CreateOrganizationDTO;
import kr.hs.dgsw.grouptime.dto.OrganizationDTO;
import kr.hs.dgsw.grouptime.dto.OrganizationResponseDTO;
import kr.hs.dgsw.grouptime.dto.UserDTO;
import kr.hs.dgsw.grouptime.mapper.AffiliationMapper;
import kr.hs.dgsw.grouptime.mapper.OrganizationMapper;
import kr.hs.dgsw.grouptime.mapper.UserMapper;
import kr.hs.dgsw.grouptime.repository.AffiliationRepository;
import kr.hs.dgsw.grouptime.repository.OrganizationRepository;
import kr.hs.dgsw.grouptime.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final UserRepository userRepository;
    private final AffiliationRepository affiliationRepository;
    private final OrganizationMapper organizationMapper;
    private final UserMapper userMapper;

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

            return organization.getOrganizationId();
        }

        return null;
    }

    public OrganizationResponseDTO getOrganization(Long organizationId) {
        Optional<Organization> organization = organizationRepository.findById(organizationId);

        if (organization.isPresent()) {
            Organization organizationEntity = organization.get();

            List<UserDTO> userList = organizationEntity
                    .getAffiliationList()
                    .stream()
                    .map((user)->userMapper.entityToDto(user.getUser()))
                    .toList();

            return new OrganizationResponseDTO(organizationMapper.entityToDto(organizationEntity), userList);
        }
        return null;
    }

    public void update(OrganizationDTO organizationDTO) {
        Optional<Organization> organization = organizationRepository.findById(organizationDTO.getOrganizationId());

        if (organization.isPresent()) {
            Organization organizationEntity = organization.get();

            organizationEntity.update(organizationEntity.getName(), organizationEntity.getEmail());

            organizationRepository.save(organizationEntity);
        }
    }

    public void delete(Long organizationId) {
        Optional<Organization> organization = organizationRepository.findById(organizationId);

        if (organization.isPresent()) {
            organizationRepository.delete(organization.get());
        }
    }
}
