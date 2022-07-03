package com.example.numberconvertor.Convertor.RU.TextToNumber;

import com.example.numberconvertor.Convertor.Interfaces.IStringToNumber;

public class RuConvertorToIntContext {
    private IStringToNumber _strategy;

    public RuConvertorToIntContext() {
    }

    public void SetConvertToIntStrategy(IStringToNumber strategy) {
        this._strategy = strategy;
    }

    public int DoConvertToInt(String textNum) {
        if (_strategy == null)
            return 0;
        return this._strategy.StringToNumber(textNum);
    }

}
