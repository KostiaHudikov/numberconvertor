package com.example.numberconvertor.repos;

import com.example.numberconvertor.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepos extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
}
