package ru.smsoft.numberconvertor.convertor.eng.numbertotext;

import ru.smsoft.numberconvertor.convertor.interfaces.IConvertNumber;
import ru.smsoft.numberconvertor.repos.EngDictionaryRepos;
import org.springframework.data.jpa.repository.JpaRepository;

public class EngHundredAdd implements IConvertNumber {
    public String NumberToString(long num, JpaRepository DictionaryRepo) {
        if (num == 0) {
            return "";
        }
        EngDictionaryRepos engDictionaryRepo = (EngDictionaryRepos) DictionaryRepo;
        String convertedNumber = new EngFirstAdd().NumberToString(num, engDictionaryRepo)
                + " "
                + engDictionaryRepo.findByIntnumberAndNumbertype(1, "hundred");
        if (convertedNumber != null) {
            return convertedNumber;
        }
        return "";
    }
}

