package com.example.numberconvertor.convertor.ru.numbertotext;

import com.example.numberconvertor.convertor.interfaces.IConvertNumber;
import org.springframework.data.jpa.repository.JpaRepository;


public class RuConvertorContext {

    private IConvertNumber _strategy;

    public RuConvertorContext() {
    }

    public void SetConvertStrategy(IConvertNumber strategy) {
        this._strategy = strategy;
    }

    public String DoConvertToText(int num, JpaRepository ruDictionaryRepo) {
        if (_strategy == null)
            return "";
        String convertedNumber = this._strategy.NumberToString(num, ruDictionaryRepo);
        if (convertedNumber != null && !convertedNumber.equals("")) {
            return convertedNumber + " ";
        }
        return "";
    }
}
