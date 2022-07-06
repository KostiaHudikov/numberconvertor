package ru.smsoft.numberconvertor.convertor.eng.numbertotext;

import ru.smsoft.numberconvertor.convertor.interfaces.IConvertNumber;
import ru.smsoft.numberconvertor.repos.EngDictionaryRepos;
import org.springframework.data.jpa.repository.JpaRepository;

public class EngTeensAdd implements IConvertNumber {
    public String NumberToString(long num, JpaRepository DictionaryRepo) {
        EngDictionaryRepos engDictionaryRepo = (EngDictionaryRepos) DictionaryRepo;
        String convertedNumber = engDictionaryRepo.findByIntnumberAndNumbertype(num, "teen");
        if (convertedNumber != null) {
            return convertedNumber;
        }
        return "";
    }
}
