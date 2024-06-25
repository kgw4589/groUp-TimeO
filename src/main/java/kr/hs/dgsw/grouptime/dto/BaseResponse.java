package kr.hs.dgsw.grouptime.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseResponse<T> {
    private Integer code;
    private String message;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private T data;

    public BaseResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
