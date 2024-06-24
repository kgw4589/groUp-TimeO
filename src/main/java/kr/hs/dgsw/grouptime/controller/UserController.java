package kr.hs.dgsw.grouptime.controller;

import kr.hs.dgsw.grouptime.domain.User;
import kr.hs.dgsw.grouptime.dto.BaseResponse;
import kr.hs.dgsw.grouptime.dto.UserDTO;
import kr.hs.dgsw.grouptime.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    public BaseResponse<UserDTO> get(@PathVariable Long userId) {
        return new BaseResponse(200, "유저 조회 성공", userService.getUser(userId));
    }

    @PostMapping
    public BaseResponse<Long> create(@RequestBody UserDTO userDTO) {
        return new BaseResponse(200, "유저 가입 성공", userService.createUser(userDTO));
    }

    @PutMapping
    public BaseResponse<Void> modify(@RequestBody UserDTO userDTO) {
        userService.modify(userDTO);
        return new BaseResponse(200, "user modify success");
    }

    @DeleteMapping("/{userId}")
    public BaseResponse<Void> delete(@PathVariable Long userId) {
        userService.delete(userId);

        return new BaseResponse(200, "user delete success");
    }
}
