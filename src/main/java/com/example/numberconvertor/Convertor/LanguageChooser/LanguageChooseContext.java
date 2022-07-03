package com.example.numberconvertor.Convertor.LanguageChooser;

import com.example.numberconvertor.Convertor.Interfaces.ILanguageChoose;
import com.example.numberconvertor.repos.DictionaryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LanguageChooseContext {
    private ILanguageChoose _strategy;
    public LanguageChooseContext()
    {
    }
    public void SetConvertToIntStrategy(ILanguageChoose strategy)
    {
        this._strategy = strategy;
    }
    public int DoConvertTextToNumber(String textNum)
    {
        if (_strategy == null)
            return 0;
        return this._strategy.ConvertTextToNumber(textNum);
    }
    public String DoConvertNumberToText(int number)
    {
        if (_strategy == null)
            return "";
        return this._strategy.ConvertNumberToText(number);
    }

}
