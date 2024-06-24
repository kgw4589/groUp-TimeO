package kr.hs.dgsw.grouptime.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class CreateOrganizationDTO {
    private Long userId;
    private OrganizationDTO organization;
}
