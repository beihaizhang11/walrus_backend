package com.walrus.server.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.walrus.common.result.Result;
import com.walrus.server.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class JwtInterceptor implements HandlerInterceptor {
    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper;

    public JwtInterceptor(JwtUtil jwtUtil, ObjectMapper objectMapper) {
        this.jwtUtil = jwtUtil;
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果是登录相关的请求，直接放行
        if (request.getRequestURI().contains("/api/auth/")) {
            return true;
        }

        // 如果是静态资源或登录页面，直接放行
        if (request.getRequestURI().startsWith("/static/") || 
            request.getRequestURI().equals("/login")) {
            return true;
        }

        String token = request.getHeader("Authorization");
        
        if (token == null || token.isEmpty()) {
            handleUnauthorized(response, "未登录");
            return false;
        }

        if (!jwtUtil.validateToken(token)) {
            handleUnauthorized(response, "token无效或已过期");
            return false;
        }

        return true;
    }

    private void handleUnauthorized(HttpServletResponse response, String message) throws Exception {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(objectMapper.writeValueAsString(Result.error(401, message)));
    }
} 