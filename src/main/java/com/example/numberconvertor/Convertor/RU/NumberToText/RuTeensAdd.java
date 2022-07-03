package com.example.numberconvertor.Convertor.RU.NumberToText;

import com.example.numberconvertor.repos.DictionaryRepo;
import com.example.numberconvertor.Convertor.Interfaces.IConvertNumber;
import org.springframework.beans.factory.annotation.Autowired;

public class RuTeensAdd implements IConvertNumber {
    @Autowired
    DictionaryRepo dictionaryRepo;

    public String NumberToString(int num) {
        return dictionaryRepo.findByIntnumberAndNumbertype(num, "teen");
    }
}

