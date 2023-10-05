package com.hexadecimal.example.exception.handler;

import com.hexadecimal.example.enums.ResponseCodeEnum;
import com.hexadecimal.example.exception.BaseException;
import com.hexadecimal.example.res.ResultDTO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理类
 */
@RestControllerAdvice
@Slf4j
public class BaseExceptionHandler {

    /**
     * 处理BaseException
     *
     * @param response
     * @param e
     * @return
     */
    @ExceptionHandler(BaseException.class)
    public ResultDTO<Void> handlerGlobalException(HttpServletResponse response, BaseException e) {
        log.error("请求异常：", e);
        response.setStatus(e.getResponseCode().getCode());

        return ResultDTO.error(e.getResponseCode(), e);
    }

    /**
     * 处理BindException
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultDTO<Void> handlerBindException(BindException e) {
        log.error("请求异常：", e);
        BindingResult bindingResult = e.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        assert fieldError != null;
        String defaultMessage = fieldError.getDefaultMessage();

        return ResultDTO.error(ResponseCodeEnum.BAD_REQUEST, defaultMessage);
    }

    /**
     * 处理Exception
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultDTO<Void> handlerException(Exception e) {
        log.error("请求异常：", e);

        return ResultDTO.error(ResponseCodeEnum.ERROR, e);
    }

}