package kr.hs.dgsw.grouptime.mapper;

import kr.hs.dgsw.grouptime.domain.Organization;
import kr.hs.dgsw.grouptime.dto.OrganizationDTO;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMapper {
    public OrganizationDTO entityToDto(Organization organization) {
        return OrganizationDTO.builder()
                .organizationId(organization.getOrganizationId())
                .name(organization.getName())
                .email(organization.getEmail())
                .build();
    }

    public Organization dtoToEntity(OrganizationDTO organizationDTO) {
        return Organization.builder()
                .email(organizationDTO.getEmail())
                .organizationId(organizationDTO.getOrganizationId())
                .name(organizationDTO.getName())
                .build();
    }
}
