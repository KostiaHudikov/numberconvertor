package com.example.numberconvertor.convertor.eng.numbertotext;

import com.example.numberconvertor.convertor.interfaces.IConvertNumber;
import com.example.numberconvertor.repos.EngDictionaryRepos;
import org.springframework.data.jpa.repository.JpaRepository;

public class EngTeensAdd implements IConvertNumber {
    public String NumberToString(int num, JpaRepository DictionaryRepo) {
        EngDictionaryRepos engDictionaryRepo = (EngDictionaryRepos) DictionaryRepo;
        String convertedNumber =  engDictionaryRepo.findByIntnumberAndNumbertype(num, "teen");
        if (convertedNumber != null) {
            return convertedNumber;
        }
        return "";
    }
}
