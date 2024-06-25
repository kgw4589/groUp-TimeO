package kr.hs.dgsw.grouptime.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Long userId;

    private String name;

    private String email;

    private String password;
}
