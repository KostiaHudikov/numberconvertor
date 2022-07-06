package com.example.numberconvertor.convertor.languagechooser;

import com.example.numberconvertor.convertor.interfaces.ILanguageChoose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class LanguageChooseContext {


    private ILanguageChoose _strategy;

    public LanguageChooseContext() {
    }

    public void SetConvertToIntStrategy(ILanguageChoose strategy) {
        this._strategy = strategy;
    }

    public int DoConvertTextToNumber(String textNum, JpaRepository DictionaryRepo) {
        if (_strategy == null)
            return 0;
        return this._strategy.ConvertTextToNumber(textNum, DictionaryRepo);
    }

    public String DoConvertNumberToText(int number, JpaRepository DictionaryRepo) {

        return this._strategy.ConvertNumberToText(number, DictionaryRepo);
    }

}
