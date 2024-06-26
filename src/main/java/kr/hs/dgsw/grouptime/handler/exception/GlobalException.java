package kr.hs.dgsw.grouptime.handler.exception;

import jdk.jshell.spi.ExecutionControl;

public class GlobalException extends BusinessException {
    private static final GlobalException NOT_FOUND = new GlobalException(ErrorCode.USER_NOT_FOUND);
    private static final GlobalException PASSWORD_IS_NOT_EQUAL = new GlobalException(ErrorCode.PASSWORD_IS_NOT_EQUAL);
    private static final GlobalException ORGANIZATION_NOT_FOUND = new GlobalException(ErrorCode.ORGANIZATION_NOT_FOUND);
    private static final GlobalException SCHEDULE_NOT_FOUND = new GlobalException(ErrorCode.SCHEDULE_NOT_FOUND);
    private static final GlobalException BAD_REQUEST_QUERIES = new GlobalException(ErrorCode.BAD_REQUEST_QUERIES);
    private static final GlobalException ENTRY_NOT_FOUND = new GlobalException(ErrorCode.ENTRY_NOT_FOUND);
    private static final GlobalException COMMENT_NOT_FOUND = new GlobalException(ErrorCode.COMMENT_NOT_FOUND);

    public GlobalException(ErrorCode errorCode) {
        super(errorCode);
    }

    public static GlobalException userNotFound() { return NOT_FOUND; }

    public static GlobalException passwordIsNotEqual() { return PASSWORD_IS_NOT_EQUAL; }

    public static GlobalException organizationNotFound() { return ORGANIZATION_NOT_FOUND; }

    public static GlobalException scheduleNotFound() { return SCHEDULE_NOT_FOUND; }

    public static GlobalException badRequestQueries() { return BAD_REQUEST_QUERIES; }

    public static GlobalException entryNotFound() { return ENTRY_NOT_FOUND; }

    public static GlobalException commentNotFound() { return COMMENT_NOT_FOUND; }
}
