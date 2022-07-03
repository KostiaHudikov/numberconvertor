package com.example.numberconvertor.repos;

import com.example.numberconvertor.Convertor.RuDictionaryContainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DictionaryRepo extends JpaRepository<RuDictionaryContainer, Long> {
    @Query(value = "SELECT r.stringnumber FROM RuDictionaryContainer r WHERE r.intnumber=?1 AND r.numbertype=?2")
    String findByIntnumberAndNumbertype(int intnumber, String numbertype);
}
