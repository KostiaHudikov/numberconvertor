package com.example.numberconvertor.repos;

import com.example.numberconvertor.convertor.eng.EngDictionaryContainer;
import com.example.numberconvertor.domain.Logs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogsRepos extends JpaRepository<Logs, Long> {
}
