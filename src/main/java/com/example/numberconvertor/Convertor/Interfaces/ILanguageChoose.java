package com.example.numberconvertor.Convertor.Interfaces;

import com.example.numberconvertor.repos.DictionaryRepo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public interface ILanguageChoose {
    String ConvertNumberToText(int number);
    int ConvertTextToNumber(String text);
}

