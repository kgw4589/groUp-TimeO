package kr.hs.dgsw.grouptime.handler.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(500, "서버 에러"),
    USER_NOT_FOUNT(404, "유저를 찾을 수 없음"),
    ORGANIZATION_NOT_FOUNT(404, "단체를 찾을 수 없음"),
    PASSWORD_IS_NOT_EQUAL(400, "비밀번호가 다름")
    ;

    private final Integer code;
    private final String message;
}
