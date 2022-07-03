package com.example.numberconvertor.Convertor.RU;

import com.example.numberconvertor.Convertor.Interfaces.ILanguageChoose;
import com.example.numberconvertor.Convertor.RU.NumberToText.*;
import com.example.numberconvertor.Convertor.RU.TextToNumber.*;
import com.example.numberconvertor.repos.DictionaryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class RuNumberConvertor implements ILanguageChoose {

    @Autowired
    DictionaryRepo dictionaryRepo;

    public String ConvertNumberToText(int number) {
        boolean isTeen = true;
        String numberString = String.valueOf(number);
        int numLenght = numberString.length();
        StringBuilder finalText = new StringBuilder();
        for (int i = numLenght; i > 0; i--) {
            int numberToConvert = Character.getNumericValue(numberString.charAt(numLenght - i));
            var context = new RuConvertorContext();
            switch (i) {
                case 1:
                    if (isTeen) {
                        if (number != 0) {
                            context.SetConvertStrategy(new RuFirstsAdd());
                        } else {
                            return "ноль";
                        }
                    }
                    break;
                case 2:
                    int numberValue = Integer.parseInt(Character.toString(numberString.charAt(numLenght - 2)) + Character.toString(numberString.charAt(numLenght - 1)));
                    if (numberValue < 20 && numberValue >= 10) {
                        isTeen = false;
                        context.SetConvertStrategy(new RuTeensAdd());
                        numberToConvert = Character.getNumericValue(numberString.charAt(numLenght - 1));
                    } else {
                        context.SetConvertStrategy(new RuTensAdd());
                    }
                    break;
                case 3:
                    context.SetConvertStrategy(new RuHundredsAdd());
                    break;
                case 4:
                    String thousandNum = Character.toString(numberString.charAt(0));
                    if (numLenght >= 5) {
                        thousandNum += Character.toString(numberString.charAt(i - 3));
                        if (numLenght >= 6) {
                            thousandNum += Character.toString(numberString.charAt(i - 2));
                        }
                    }
                    numberToConvert = Integer.parseInt(thousandNum);
                    context.SetConvertStrategy(new ThousandAdd());
                    break;
            } finalText.append(context.DoConvertToText(numberToConvert));
        } return finalText.toString();
    }

    public int ConvertTextToNumber(String number) {
        String[] numberList = number.split(" ");
        int finalNumber = 0;
        for (String s : numberList) {
            boolean isMultiplication = false;
            RuConvertorToIntContext doConvert = new RuConvertorToIntContext();
            RuStringChecker isSomeNum = new RuStringChecker();
            if (isSomeNum.FirstChecker(s)) {
                doConvert.SetConvertToIntStrategy(new RuConvertFitstsToInt());
            }
            if (isSomeNum.TeenChecker(s)) {
                doConvert.SetConvertToIntStrategy(new RuConvertTeensToInt());
            }
            if (isSomeNum.TenChecker(s)) {
                doConvert.SetConvertToIntStrategy(new RuConvertTensToInt());
            }
            if (isSomeNum.HundredChecker(s)) {
                doConvert.SetConvertToIntStrategy(new RuConvertHundredToInt());
            }
            if (isSomeNum.ThousendChecker(s)) {
                isMultiplication = true;
                doConvert.SetConvertToIntStrategy(new RuConvertThousendToInt());
            }
            if (isMultiplication) {
                finalNumber *= doConvert.DoConvertToInt(s);
            } else {
                finalNumber += doConvert.DoConvertToInt(s);
            }
        }
        return finalNumber;
    }
}

