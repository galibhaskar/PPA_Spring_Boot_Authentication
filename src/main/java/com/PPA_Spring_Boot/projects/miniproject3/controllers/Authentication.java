package com.PPA_Spring_Boot.projects.miniproject3.controllers;

import com.PPA_Spring_Boot.projects.miniproject3.models.Users;
import com.PPA_Spring_Boot.projects.miniproject3.services.UserService;
import com.PPA_Spring_Boot.projects.miniproject3.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
public class Authentication {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(method = RequestMethod.POST, path = "/signup", produces = "application/json")
    public ResponseEntity<String> signupUser(@RequestBody Map<String, String> body) {
        String name = body.get("name");

        String email = body.get("email");

        String password = passwordEncoder.encode(body.get("password"));

        String authToken = JWTUtil.generateToken(email);

        String refreshToken = JWTUtil.generateRefreshToken(email);

        userService.createUser(name, email, password, refreshToken);

        return ResponseEntity.ok()
                .header("token", authToken)
                .header("refresh-token", refreshToken)
                .body(authToken);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/login", produces = "application/json")
    public ResponseEntity<String> loginUser(@RequestBody Map<String, String> body) {
        String email = body.get("email");

        Optional<Users> user = userService.getUserByEmail(email);

        if (user.isEmpty())
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("User not found");

        boolean isValidCredentials = passwordEncoder
                .matches(body.get("password"), user.get().getPassword());

        if (!isValidCredentials)
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Invalid Password");

        String authToken = JWTUtil.generateToken(email);

        String refreshToken;

        if (!JWTUtil.isTokenExpired(user.get().getRefreshToken()))
            refreshToken = user.get().getRefreshToken();

        else {
            refreshToken = JWTUtil.generateRefreshToken(email);
            userService.updateRefreshToken(email, refreshToken);
        }

        return ResponseEntity.ok()
                .header("token", authToken)
                .header("refresh-token", refreshToken)
                .body(authToken);
    }
}
