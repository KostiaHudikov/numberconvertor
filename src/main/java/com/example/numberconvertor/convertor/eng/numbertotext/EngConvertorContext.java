package com.example.numberconvertor.convertor.eng.numbertotext;

import com.example.numberconvertor.convertor.interfaces.IConvertNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public class EngConvertorContext {
    private IConvertNumber _strategy;

    public void SetConvertStrategy(IConvertNumber strategy) {
        this._strategy = strategy;
    }

    public String DoConvertToText(int num, JpaRepository DictionaryRepo) {
        if (_strategy == null)
            return "";
        String convertedNumber = this._strategy.NumberToString(num, DictionaryRepo);
        if (convertedNumber != null && !convertedNumber.equals("")) {
            return convertedNumber + " ";
        }
        return "";
    }

}
