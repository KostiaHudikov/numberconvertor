package ru.smsoft.numberconvertor.repos;

import ru.smsoft.numberconvertor.postgress.convertor.RuDictionaryContainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RuDictionaryRepos extends JpaRepository<RuDictionaryContainer, Long> {
    @Query(value = "SELECT r.stringnumber FROM RuDictionaryContainer r WHERE r.intnumber=?1 AND r.numbertype=?2")
    String findByIntnumberAndNumbertype(long intnumber, String numbertype);

    @Query(value = "SELECT r.fullnumber FROM RuDictionaryContainer r WHERE r.stringnumber=?1")
    long findByStringnumber(String stringnumber);
}
