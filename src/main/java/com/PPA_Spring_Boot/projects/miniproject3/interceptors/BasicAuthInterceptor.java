package com.PPA_Spring_Boot.projects.miniproject3.interceptors;

import com.PPA_Spring_Boot.projects.miniproject3.models.Users;
import com.PPA_Spring_Boot.projects.miniproject3.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

@Component
public class BasicAuthInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader("Authorization");

        String encodedCredentials = authHeader.split(" ")[1];

        byte[] decoded = Base64.getDecoder().decode(encodedCredentials);

        String decodedHeader = new String(decoded, StandardCharsets.UTF_8);

        String[] decodedCredentials = decodedHeader.split(":");

        String email = decodedCredentials[0];

        Optional<Users> user = userService.getUserByEmail(email);

        if (user.isPresent() && passwordEncoder
                .matches(decodedCredentials[1], user.get().getPassword())) {
            request.setAttribute("isValid", true);
            return true;
        }

        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Authentication Failed");

        return false;
    }
}
