package com.example.numberconvertor.Convertor.ENG;

import com.example.numberconvertor.Convertor.ENG.NumberToText.*;
import com.example.numberconvertor.Convertor.ENG.TextToNumber.*;
import com.example.numberconvertor.Convertor.Interfaces.ILanguageChoose;

public class EngNumberConvertor implements ILanguageChoose {

    public String ConvertNumberToText(int number) {
        boolean isTeen = true;
        String numberString = String.valueOf(number);
        int numLenght = numberString.length();
        StringBuilder finalText = new StringBuilder();
        for (int i = numLenght; i > 0; i--) {
            int numberToConvert = Character.getNumericValue(numberString.charAt(numLenght - i));
            EngConvertorContext context = new EngConvertorContext();
            switch (i) {
                case 1:
                    if (isTeen) {
                        if (number != 0) {
                            context.SetConvertStrategy(new EngFirstAdd());
                        } else {
                            return "zero";
                        }
                    }
                    break;
                case 2:
                    int numberValue = Integer.parseInt(Character.toString(numberString.charAt(numLenght - 2)) + Character.toString(numberString.charAt(numLenght - 1)));
                    if (numberValue < 20 && numberValue >= 10) {
                        isTeen = false;
                        context.SetConvertStrategy(new EngTeensAdd());
                        numberToConvert = Character.getNumericValue(numberString.charAt(numLenght - 1));
                    } else {
                        context.SetConvertStrategy(new EngTensAdd());
                    }
                    break;
                case 3:
                    context.SetConvertStrategy(new EngHundredAdd());
                    break;
                case 4:
                    String thousandNum = Character.toString(numberString.charAt(0));
                    if (numLenght >= 5) {
                        thousandNum += Character.toString(numberString.charAt(i - 3));
                        if (numLenght >= 6) {
                            thousandNum += Character.toString(numberString.charAt(i - 2));
                            if (numLenght >= 7) {
                                thousandNum += Character.toString(numberString.charAt(i - 1));
                            }
                        }
                    }
                    numberToConvert = Integer.parseInt(thousandNum);
                    context.SetConvertStrategy(new EngThousandAdd());
                    break;
            }
            finalText.append(context.DoConvertToText(numberToConvert));
        }
        return finalText.toString();
    }

    public int ConvertTextToNumber(String number) {
        String[] numberList = number.split(" ");
        int finalNumber = 0;
        int tmpFirst = 0;
        boolean isFirstMultiplication = false;
        boolean isThousandMultiplication = false;
        boolean isHundredMultiplication = false;
        for (String s : numberList) {
            boolean isMultiplication = false;
            EngConvertorToIntContext doConvert = new EngConvertorToIntContext();
            EngStringChecker isSomeNum = new EngStringChecker();
            if (isSomeNum.FirstChecker(s)) {
                if (isThousandMultiplication) {
                    isFirstMultiplication = true;
                    isMultiplication = true;
                }
                doConvert.SetConvertToIntStrategy(new EngConvertFitstsToInt());
            }
            if (isSomeNum.TeenChecker(s)) {
                doConvert.SetConvertToIntStrategy(new EngConvertTeensToInt());
            }
            if (isSomeNum.TenChecker(s)) {
                doConvert.SetConvertToIntStrategy(new EngConvertTensToInt());
            }
            if (isSomeNum.HundredChecker(s)) {
                isMultiplication = true;
                isHundredMultiplication = true;
                doConvert.SetConvertToIntStrategy(new EngConvertHundredToInt());
            }
            if (isSomeNum.ThousendChecker(s)) {
                isMultiplication = true;
                isThousandMultiplication = true;
                doConvert.SetConvertToIntStrategy(new EngConvertThousendToInt());
            }
            if (isMultiplication) {
                if (isFirstMultiplication) {
                    tmpFirst += doConvert.DoConvertToInt(s);
                    isFirstMultiplication = false;
                } else {
                    if (isThousandMultiplication && isHundredMultiplication) {
                        finalNumber += tmpFirst * doConvert.DoConvertToInt(s);
                        isThousandMultiplication = false;
                        isHundredMultiplication = false;
                    } else {
                        finalNumber *= doConvert.DoConvertToInt(s);
                        isHundredMultiplication = false;
                    }
                }
            } else {
                finalNumber += doConvert.DoConvertToInt(s);
            }
        }
        return finalNumber;
    }
}

