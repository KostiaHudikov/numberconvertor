package ru.smsoft.numberconvertor.convertor.languagechooser;

import ru.smsoft.numberconvertor.convertor.interfaces.ILanguageChoose;
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

    public long DoConvertTextToNumber(String textNum, JpaRepository DictionaryRepo) {
        if (_strategy == null)
            return 0;
        return this._strategy.ConvertTextToNumber(textNum, DictionaryRepo);
    }

    public String DoConvertNumberToText(long number, JpaRepository DictionaryRepo) {

        return this._strategy.ConvertNumberToText(number, DictionaryRepo);
    }

}
