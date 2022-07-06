package ru.smsoft.numberconvertor.repos;

import ru.smsoft.numberconvertor.postgress.convertor.EngDictionaryContainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EngDictionaryRepos extends JpaRepository<EngDictionaryContainer, Long> {
    @Query(value = "SELECT r.stringnumber FROM EngDictionaryContainer r WHERE r.intnumber=?1 AND r.numbertype=?2")
    String findByIntnumberAndNumbertype(long intnumber, String numbertype);

    @Query(value = "SELECT r.fullnumber FROM EngDictionaryContainer r WHERE r.stringnumber=?1")
    long findByStringnumber(String stringnumber);
}
