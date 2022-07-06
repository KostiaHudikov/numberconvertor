package ru.smsoft.numberconvertor.convertor.eng.numbertotext;

import ru.smsoft.numberconvertor.convertor.interfaces.IConvertNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public class EngThousandAdd implements IConvertNumber {
    public String NumberToString(long number, JpaRepository DictionaryRepo) {
        return EngRangeConvert.NumberToString(number,DictionaryRepo,"thousand");
    }
}

