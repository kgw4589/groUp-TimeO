package kr.hs.dgsw.grouptime.handler;

import kr.hs.dgsw.grouptime.dto.BaseResponse;
import kr.hs.dgsw.grouptime.handler.exception.BusinessException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<BaseResponse> handleException(BusinessException e) {
        BaseResponse response = new BaseResponse(e.getError().getCode(), e.getError().getMessage());
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(e.getError().getCode()));
    }
}
