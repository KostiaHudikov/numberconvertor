package ru.smsoft.numberconvertor.convertor.interfaces;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ILanguageChoose {
    String ConvertNumberToText(long number, JpaRepository DictionaryRepo);

    long ConvertTextToNumber(String text, JpaRepository DictionaryRepo);
}

