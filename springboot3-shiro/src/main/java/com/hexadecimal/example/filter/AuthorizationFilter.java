package com.hexadecimal.example.filter;

import com.alibaba.fastjson.JSON;
import com.hexadecimal.example.enums.ResponseCodeEnum;
import com.hexadecimal.example.res.ResultDTO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 鉴权过滤器
 */
@WebFilter("authorizationFilter")
public class AuthorizationFilter implements Filter {
    /**
     * 静态资源文件/文件夹
     */
    private static final List<String> STATIC_RESOURCES;
    private static final String LOGIN_URI = "/user/login";
    private static final String SEPARATOR_URI = "/";

    static {
        STATIC_RESOURCES = new ArrayList<>();

        STATIC_RESOURCES.add("/index.html");
        STATIC_RESOURCES.add("/login.html");
        STATIC_RESOURCES.add("/images/");
        STATIC_RESOURCES.add("/html/");
        STATIC_RESOURCES.add("/css/");
        STATIC_RESOURCES.add("/js/");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        String requestURI = request.getRequestURI();
        if (SEPARATOR_URI.equals(requestURI) || LOGIN_URI.equals(requestURI)) {
            chain.doFilter(req, resp);
            return;
        }

        for (String resource : STATIC_RESOURCES) {
            if (requestURI.contains(resource)) {
                chain.doFilter(req, resp);
                return;
            }
        }

        Subject subject = SecurityUtils.getSubject();
        if (subject != null && !subject.isPermitted(requestURI)) {
            HttpServletResponse response = (HttpServletResponse) resp;
            response.setContentType("application/json;charset=utf-8");

            ResultDTO resultDTO = ResultDTO.error(ResponseCodeEnum.UNAUTHORIZED, "正在访问未授权的资源");
            String data = JSON.toJSONString(resultDTO);

            response.getWriter().write(data);
            return;
        }

        chain.doFilter(req, resp);
    }

}