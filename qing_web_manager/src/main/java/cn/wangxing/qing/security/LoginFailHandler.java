package cn.wangxing.qing.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import java.io.IOException;

public class LoginFailHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setHeader("Content-Type","application/json;charset=utf-8");
        response.getWriter().write("{\"errorCode\":\"400\",\"message\":\"密码或用户名错误\"}");
    }
}
