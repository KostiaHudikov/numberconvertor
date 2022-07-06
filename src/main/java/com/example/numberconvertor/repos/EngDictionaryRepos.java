package com.example.numberconvertor.repos;

import com.example.numberconvertor.convertor.eng.EngDictionaryContainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EngDictionaryRepos extends JpaRepository<EngDictionaryContainer, Long> {
    @Query(value = "SELECT r.stringnumber FROM EngDictionaryContainer r WHERE r.intnumber=?1 AND r.numbertype=?2")
    String findByIntnumberAndNumbertype(int intnumber, String numbertype);

    @Query(value = "SELECT r.fullnumber FROM EngDictionaryContainer r WHERE r.stringnumber=?1")
    int findByStringnumber(String stringnumber);
}
