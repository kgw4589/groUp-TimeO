package kr.hs.dgsw.grouptime.mapper;

import kr.hs.dgsw.grouptime.domain.User;
import kr.hs.dgsw.grouptime.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    public UserDTO entityToDto(User user) {
        return UserDTO.builder()
                .userid(user.getUserId())
                .email(user.getEmail())
                .name(user.getName())
                .password(user.getPassword())
                .build();
    }

    public User dtoToEntity(UserDTO userDTO) {
        return User.builder()
                .userId(userDTO.getUserid())
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();
    }
}
