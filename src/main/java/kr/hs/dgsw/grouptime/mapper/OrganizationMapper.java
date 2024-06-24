package kr.hs.dgsw.grouptime.mapper;

import kr.hs.dgsw.grouptime.domain.Organization;
import kr.hs.dgsw.grouptime.dto.OrganizationDTO;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMapper {
    public OrganizationDTO entityToDto(Organization organization) {
        return OrganizationDTO.builder()
                .groupId(organization.getGroupId())
                .name(organization.getName())
                .email(organization.getEmail())
                .build();
    }

    public Organization dtoToEntity(OrganizationDTO organizationDTO) {
        return Organization.builder()
                .email(organizationDTO.getEmail())
                .groupId(organizationDTO.getGroupId())
                .name(organizationDTO.getName())
                .build();
    }
}
