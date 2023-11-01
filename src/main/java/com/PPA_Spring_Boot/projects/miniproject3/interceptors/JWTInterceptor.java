package com.PPA_Spring_Boot.projects.miniproject3.interceptors;

import com.PPA_Spring_Boot.projects.miniproject3.utils.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String authHeader = request.getHeader("Authorization");

            String[] header = authHeader.split(" ");

            System.out.println(header[1]);

            if (JWTUtil.validateToken(header[1]))
                return true;

            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Authentication Failed");

            return false;
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Authentication Failed");
            return false;
        }
    }
}
