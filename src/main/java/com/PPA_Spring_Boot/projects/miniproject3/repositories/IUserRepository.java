package com.PPA_Spring_Boot.projects.miniproject3.repositories;

import com.PPA_Spring_Boot.projects.miniproject3.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByEmail(String email);

    Optional<Users> findByEmailAndPassword(String email, String password);

    @Query("update Users users set users.refreshToken=:refreshToken where users.email=:email")
    void updateByEmail(@Param("email") String email,
                       @Param("refreshToken") String refreshToken);
}
