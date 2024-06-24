package kr.hs.dgsw.grouptime.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserResponseDTO {
    private UserDTO user;

    private List<OrganizationDTO> organizationList;
}
