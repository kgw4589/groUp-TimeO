package kr.hs.dgsw.grouptime.handler.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final ErrorCode error;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.error = errorCode;
    }
}
