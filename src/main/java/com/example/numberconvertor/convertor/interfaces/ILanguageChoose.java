package com.example.numberconvertor.convertor.interfaces;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ILanguageChoose{
    String ConvertNumberToText(int number,JpaRepository DictionaryRepo);
    int ConvertTextToNumber(String text,JpaRepository DictionaryRepo);
}

