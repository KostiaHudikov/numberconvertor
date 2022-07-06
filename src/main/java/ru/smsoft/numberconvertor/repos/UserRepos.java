package ru.smsoft.numberconvertor.repos;

import ru.smsoft.numberconvertor.postgress.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepos extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
}
