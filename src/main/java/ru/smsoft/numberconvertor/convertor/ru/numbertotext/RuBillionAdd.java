package ru.smsoft.numberconvertor.convertor.ru.numbertotext;

import ru.smsoft.numberconvertor.convertor.interfaces.IConvertNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public class RuBillionAdd implements IConvertNumber {

    public String NumberToString(long number, JpaRepository DictionaryRepo) {
        return RuRangeConvert.NumberToString(number, DictionaryRepo, "billion");
    }
}

