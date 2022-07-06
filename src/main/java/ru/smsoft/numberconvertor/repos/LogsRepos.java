package ru.smsoft.numberconvertor.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.smsoft.numberconvertor.postgress.logs.Logs;

@Repository
public interface LogsRepos extends JpaRepository<Logs, Long> {
}
