package ru.smsoft.numberconvertor.convertor.ru.numbertotext;

import ru.smsoft.numberconvertor.repos.RuDictionaryRepos;
import ru.smsoft.numberconvertor.convertor.interfaces.IConvertNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public class RuFirstsAdd implements IConvertNumber {

    public String NumberToString(long num, JpaRepository DictionaryRepo) {
        RuDictionaryRepos ruDictionaryRepo = (RuDictionaryRepos) DictionaryRepo;
        String convertedNumber = ruDictionaryRepo.findByIntnumberAndNumbertype(num, "first");
        if (convertedNumber != null) {
            return convertedNumber;
        }
        return "";
    }
}

