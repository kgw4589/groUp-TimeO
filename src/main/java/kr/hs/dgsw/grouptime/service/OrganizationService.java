package kr.hs.dgsw.grouptime.service;

import kr.hs.dgsw.grouptime.domain.Affiliation;
import kr.hs.dgsw.grouptime.domain.Organization;
import kr.hs.dgsw.grouptime.domain.User;
import kr.hs.dgsw.grouptime.dto.CreateOrganizationDTO;
import kr.hs.dgsw.grouptime.dto.OrganizationDTO;
import kr.hs.dgsw.grouptime.dto.OrganizationResponseDTO;
import kr.hs.dgsw.grouptime.dto.UserDTO;
import kr.hs.dgsw.grouptime.handler.exception.GlobalException;
import kr.hs.dgsw.grouptime.mapper.OrganizationMapper;
import kr.hs.dgsw.grouptime.mapper.UserMapper;
import kr.hs.dgsw.grouptime.repository.AffiliationRepository;
import kr.hs.dgsw.grouptime.repository.OrganizationRepository;
import kr.hs.dgsw.grouptime.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final UserRepository userRepository;
    private final AffiliationRepository affiliationRepository;
    private final OrganizationMapper organizationMapper;
    private final UserMapper userMapper;

    public Long createOrganization(CreateOrganizationDTO createOrganizationDTO) {
        User user = userRepository.findById(createOrganizationDTO.getUserId()).orElseThrow(GlobalException::userNotFound);

        Organization organization = organizationMapper.dtoToEntity(createOrganizationDTO.getOrganization());

        organizationRepository.save(organization);

        Affiliation affiliation = Affiliation.builder()
                .user(user)
                .organization(organization)
                .build();

        affiliationRepository.save(affiliation);

        return organization.getOrganizationId();
    }

    public OrganizationResponseDTO getOrganization(Long organizationId) {
        Organization organization = organizationRepository.findById(organizationId).orElseThrow(GlobalException::organizationNotFound);

        List<UserDTO> userList = organization
                .getAffiliationList()
                .stream()
                .map((user)->userMapper.entityToDto(user.getUser()))
                .toList();

        return new OrganizationResponseDTO(organizationMapper.entityToDto(organization), userList);
    }

    public void update(OrganizationDTO organizationDTO) {
        Organization organization = organizationRepository
                .findById(organizationDTO.getOrganizationId())
                .orElseThrow(GlobalException::organizationNotFound);

        organization.update(organizationDTO.getName(), organizationDTO.getEmail());

        organizationRepository.save(organization);
    }

    public void delete(Long organizationId) {
        Organization organization = organizationRepository
                .findById(organizationId)
                .orElseThrow(GlobalException::organizationNotFound);

        affiliationRepository.deleteAll(organization.getAffiliationList());

        organizationRepository.delete(organization);
    }
}
