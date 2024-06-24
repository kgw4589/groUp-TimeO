package kr.hs.dgsw.grouptime.service;

import kr.hs.dgsw.grouptime.domain.User;
import kr.hs.dgsw.grouptime.dto.UserDTO;
import kr.hs.dgsw.grouptime.mapper.UserMapper;
import kr.hs.dgsw.grouptime.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Long createUser(UserDTO userDTO) {
        User user = userMapper.dtoToEntity(userDTO);

        userRepository.save(user);

        return user.getUserId();
    }

    public UserDTO getUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);

        return (user.isPresent()) ? userMapper.entityToDto(user.get()) : null;
    }

    public void modify(UserDTO userDTO) {
        Optional<User> user = userRepository.findById(userDTO.getUserid());

        if (user.isPresent()) {
            User userEntity = user.get();
            userEntity.update(userDTO.getName(), userDTO.getEmail(), userDTO.getPassword());

            userRepository.save(userEntity);
        }
    }

    public void delete(Long userId) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            userRepository.delete(user.get());
        }
    }
}
