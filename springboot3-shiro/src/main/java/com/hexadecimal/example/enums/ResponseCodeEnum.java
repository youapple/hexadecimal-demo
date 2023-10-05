package com.hexadecimal.example.enums;

/**
 * 响应状态码
 */
public enum ResponseCodeEnum {
    OK(200, "请求成功"),

    BAD_REQUEST(400, "失败的请求"),

    UNAUTHORIZED(401, "未授权"),

    FORBIDDEN(403, "禁止访问"),

    NOT_FOUND(404, "请求找不到"),

    NOT_ACCEPTABLE(406, "不可访问"),

    CONFLICT(409, "冲突"),

    ERROR(500, "服务器发生异常");

    private final Integer code;

    private final String message;

    ResponseCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return getMessage();
    }


}