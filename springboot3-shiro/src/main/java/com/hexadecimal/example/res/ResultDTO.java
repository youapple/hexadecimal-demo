package com.hexadecimal.example.res;

import com.hexadecimal.example.enums.ResponseCodeEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResultDTO<T> implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 响应状态码
     */
    private Integer code;
    /**
     * 响应信息
     */
    private String message;
    /**
     * 响应数据
     */
    private T data;

    public static <T> ResultDTO<T> success() {
        return success("ok");
    }

    public static <T> ResultDTO<T> success(String message) {
        return success(message, null);
    }

    public static <T> ResultDTO<T> success(T data) {
        return success("ok", data);
    }

    public static <T> ResultDTO<T> success(String message, T data) {
        ResultDTO<T> resultDTO = new ResultDTO<>();
        resultDTO.setCode(ResponseCodeEnum.OK.getCode());
        resultDTO.setMessage(message);
        resultDTO.setData(data);
        return resultDTO;
    }

    public static <T> ResultDTO<T> error(String message) {
        return error(ResponseCodeEnum.ERROR, message);
    }

    public static <T> ResultDTO<T> error(ResponseCodeEnum responseCode, Throwable e) {
        return error(responseCode, e.getMessage() != null ? e.getMessage() : "系统异常，请联系管理员！");
    }

    public static <T> ResultDTO<T> error(ResponseCodeEnum responseCode, String message) {
        ResultDTO<T> resultDTO = new ResultDTO<>();
        resultDTO.setCode(responseCode.getCode());
        resultDTO.setMessage(message);
        return resultDTO;
    }

}