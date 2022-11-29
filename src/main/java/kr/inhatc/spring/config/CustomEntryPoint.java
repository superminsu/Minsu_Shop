package kr.inhatc.spring.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class CustomEntryPoint implements AuthenticationEntryPoint {
    
    /*
     * AccessDeniedHandler : 서버에 요청을 할 때 액세스가 가능한지 권한을 체크 후 액세스 할 수 없는 요청을 했을시 동작
     * AuthenticationEntryPoint : 인증이 되지 않은 유저가 요청했을 때 동작
     */

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        // 허용되지 않은 권한으로 접근할 경우
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "unauthorized");
    }

}
