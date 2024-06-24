package kr.hs.dgsw.grouptime.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDTO {
    private Long groupId;

    private String name;

    private String email;
}
