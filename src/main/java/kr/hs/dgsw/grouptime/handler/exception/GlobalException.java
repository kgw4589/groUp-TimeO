package kr.hs.dgsw.grouptime.handler.exception;

import jdk.jshell.spi.ExecutionControl;

public class GlobalException extends BusinessException {
    private static final GlobalException NOT_FOUND = new GlobalException(ErrorCode.USER_NOT_FOUNT);
    private static final GlobalException PASSWORD_IS_NOT_EQUAL = new GlobalException(ErrorCode.PASSWORD_IS_NOT_EQUAL);
    private static final GlobalException ORGANIZATION_NOT_FOUND = new GlobalException(ErrorCode.ORGANIZATION_NOT_FOUNT);

    public GlobalException(ErrorCode errorCode) {
        super(errorCode);
    }

    public static GlobalException userNotFound() { return NOT_FOUND; }

    public static GlobalException passwordIsNotEqual() { return PASSWORD_IS_NOT_EQUAL; }

    public static GlobalException organizationNotFound() { return ORGANIZATION_NOT_FOUND; }
}
