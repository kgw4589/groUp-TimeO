package kr.hs.dgsw.grouptime.dto;

import kr.hs.dgsw.grouptime.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class OrganizationResponseDTO {
    private OrganizationDTO organization;
    private List<UserDTO> userList;
}
