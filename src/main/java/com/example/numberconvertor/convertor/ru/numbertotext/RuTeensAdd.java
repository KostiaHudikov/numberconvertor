package com.example.numberconvertor.convertor.ru.numbertotext;

import com.example.numberconvertor.repos.RuDictionaryRepos;
import com.example.numberconvertor.convertor.interfaces.IConvertNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public class RuTeensAdd implements IConvertNumber {

    public String NumberToString(int num, JpaRepository DictionaryRepo) {
        RuDictionaryRepos ruDictionaryRepo = (RuDictionaryRepos) DictionaryRepo;
        String convertedNumber = ruDictionaryRepo.findByIntnumberAndNumbertype(num, "teen");
        if (convertedNumber != null) {
            return convertedNumber;
        }
        return "";
    }
}

