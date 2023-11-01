package com.PPA_Spring_Boot.projects.miniproject3.services;

import com.PPA_Spring_Boot.projects.miniproject3.models.Users;

import java.util.Optional;

public interface UserService {
    void createUser(String name, String email, String password, String refreshToken);

    void updateRefreshToken(String email, String refreshToken);

    Optional<Users> getUserByEmail(String email);

    Optional<Users> getUserByEmailAndPassword(String email, String password);
}
