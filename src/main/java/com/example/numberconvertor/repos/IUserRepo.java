package com.example.numberconvertor.repos;

import com.example.numberconvertor.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface IUserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
