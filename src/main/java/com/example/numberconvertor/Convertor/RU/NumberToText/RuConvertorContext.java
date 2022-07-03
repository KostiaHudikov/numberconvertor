package com.example.numberconvertor.Convertor.RU.NumberToText;

import com.example.numberconvertor.Convertor.Interfaces.IConvertNumber;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class RuConvertorContext {
    private IConvertNumber _strategy;

    public RuConvertorContext() {
    }

    public RuConvertorContext(IConvertNumber strategy) {
        this._strategy = strategy;
    }

    public void SetConvertStrategy(IConvertNumber strategy) {
        this._strategy = strategy;
    }

    public String DoConvertToText(int num) {
        if (_strategy == null)
            return "";
        return this._strategy.NumberToString(num);
    }
}
