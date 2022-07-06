package com.example.numberconvertor.convertor.eng.numbertotext;

import com.example.numberconvertor.convertor.interfaces.IConvertNumber;
import com.example.numberconvertor.repos.EngDictionaryRepos;
import org.springframework.data.jpa.repository.JpaRepository;

public class EngHundredAdd implements IConvertNumber {
    public String NumberToString(int num, JpaRepository DictionaryRepo) {
        if (num == 0) {
            return "";
        }
        EngDictionaryRepos engDictionaryRepo = (EngDictionaryRepos) DictionaryRepo;
        String convertedNumber =  new EngFirstAdd().NumberToString(num, engDictionaryRepo) + " " + engDictionaryRepo.findByIntnumberAndNumbertype(1, "hundred");
        if (convertedNumber != null) {
            return convertedNumber;
        }
        return "";
    }
}

