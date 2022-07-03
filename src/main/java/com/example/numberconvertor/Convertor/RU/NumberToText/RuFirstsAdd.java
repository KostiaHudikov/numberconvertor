package com.example.numberconvertor.Convertor.RU.NumberToText;

import com.example.numberconvertor.repos.DictionaryRepo;
import com.example.numberconvertor.Convertor.Interfaces.IConvertNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Component
public class RuFirstsAdd implements IConvertNumber {

    @Autowired
    DictionaryRepo dictionaryRepo;

    public String NumberToString(int num) {
        return dictionaryRepo.findByIntnumberAndNumbertype(num,"first");
    }
}

