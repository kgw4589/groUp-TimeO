package kr.hs.dgsw.grouptime.service;

import kr.hs.dgsw.grouptime.domain.User;
import kr.hs.dgsw.grouptime.dto.LoginDTO;
import kr.hs.dgsw.grouptime.dto.OrganizationDTO;
import kr.hs.dgsw.grouptime.dto.UserDTO;
import kr.hs.dgsw.grouptime.dto.UserResponseDTO;
import kr.hs.dgsw.grouptime.handler.exception.GlobalException;
import kr.hs.dgsw.grouptime.mapper.OrganizationMapper;
import kr.hs.dgsw.grouptime.mapper.UserMapper;
import kr.hs.dgsw.grouptime.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final OrganizationMapper organizationMapper;

    public Long createUser(UserDTO userDTO) {
        User user = userMapper.dtoToEntity(userDTO);

        userRepository.save(user);

        return user.getUserId();
    }

    public Long login(LoginDTO loginDTO) {
        User user = userRepository
                .findByEmail(loginDTO.getEmail())
                .orElseThrow(GlobalException::userNotFound);

        if (user.getPassword().equals(loginDTO.getPassword())) {
            return user.getUserId();
        } else {
            throw GlobalException.passwordIsNotEqual();
        }
    }

    public UserResponseDTO getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(GlobalException::userNotFound);

        List<OrganizationDTO> userList = user
                .getAffiliationList()
                .stream()
                .map((organization) -> organizationMapper.entityToDto(organization.getOrganization()))
                .toList();

        return new UserResponseDTO(userMapper.entityToDto(user), userList);
    }

    public void modify(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getUserId()).orElseThrow(GlobalException::userNotFound);

        user.update(userDTO.getName(), userDTO.getEmail(), userDTO.getPassword());

        userRepository.save(user);
    }

    public void delete(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(GlobalException::userNotFound);

        userRepository.delete(user);
    }
}
