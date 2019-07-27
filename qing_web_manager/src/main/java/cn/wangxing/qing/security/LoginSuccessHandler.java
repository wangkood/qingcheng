package cn.wangxing.qing.security;

import cn.wangxing.qing.pojo.system.LoginLog;
import cn.wangxing.qing.service.system.LoginLogService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Reference
    LoginLogService loginLogService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        // 登录日志记录
        loginLogService.insert(new LoginLog(
                null,
                authentication.getName(),
                request.getRemoteAddr(),
                "test",
                "test",
                new Date()
        ));



        // 返回成功数据
        response.setHeader("Content-Type","application/json;charset=utf-8");
        response.getWriter().write("{\"errorCode\":\"0\",\"message\":\"success\"}");
    }
}
