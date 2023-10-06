package com.hexadecimal.example.res;

import com.hexadecimal.example.enums.ResponseCodeEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResultDTO implements Serializable {
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
    private Object data;

    public static ResultDTO success() {
        return success("ok");
    }

    public static ResultDTO success(String message) {
        return success(message, null);
    }

    public static ResultDTO success(Object data) {
        return success("ok", data);
    }

    public static ResultDTO success(String message, Object data) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(ResponseCodeEnum.OK.getCode());
        resultDTO.setMessage(message);
        resultDTO.setData(data);
        return resultDTO;
    }

    public static ResultDTO error(String message) {
        return error(ResponseCodeEnum.ERROR, message);
    }

    public static ResultDTO error(ResponseCodeEnum responseCode, Throwable e) {
        return error(responseCode, e.getMessage() != null ? e.getMessage() : "系统异常，请联系管理员！");
    }

    public static ResultDTO error(ResponseCodeEnum responseCode, String message) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(responseCode.getCode());
        resultDTO.setMessage(message);
        return resultDTO;
    }

}