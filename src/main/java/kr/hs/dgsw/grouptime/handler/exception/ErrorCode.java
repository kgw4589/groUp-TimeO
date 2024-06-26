package kr.hs.dgsw.grouptime.handler.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(500, "서버 에러"),
    USER_NOT_FOUND(404, "유저를 찾을 수 없음"),
    ORGANIZATION_NOT_FOUND(404, "단체를 찾을 수 없음"),
    SCHEDULE_NOT_FOUND(404, "일정을 찾을 수 없음"),
    ENTRY_NOT_FOUND(404, "참가된 일정을 찾을 수 없음"),
    COMMENT_NOT_FOUND(404, "댓글을 찾을 수 없음"),
    AFFILIATION_NOT_FOUND(404, "참가한 단체를 찾을 수 없음"),
    PASSWORD_IS_NOT_EQUAL(400, "비밀번호가 다름"),
    BAD_REQUEST_QUERIES(400, "쿼리가 잘못됨")
    ;

    private final Integer code;
    private final String message;
}
