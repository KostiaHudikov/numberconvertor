package com.example.numberconvertor.Convertor.ENG.NumberToText;

import com.example.numberconvertor.Convertor.Interfaces.IConvertNumber;

public class EngConvertorContext {
    private IConvertNumber _strategy;
    public void SetConvertStrategy(IConvertNumber strategy)
    {
        this._strategy = strategy;
    }
    public String DoConvertToText(int num)
    {
        if (_strategy == null)
            return "";
        return this._strategy.NumberToString(num);
    }

}
