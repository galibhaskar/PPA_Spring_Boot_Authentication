package com.PPA_Spring_Boot.projects.miniproject3.services;

import com.PPA_Spring_Boot.projects.miniproject3.models.Users;
import com.PPA_Spring_Boot.projects.miniproject3.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public void createUser(String name, String email, String password, String refreshToken) {
        Users user = new Users(name, email, password, refreshToken);

        userRepository.save(user);
    }

    @Override
    public Optional<Users> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<Users> getUserByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public void updateRefreshToken(String email, String refreshToken) {
        userRepository.updateByEmail(email, refreshToken);
    }
}
