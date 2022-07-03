package com.example.numberconvertor.Convertor.RU.NumberToText;

import com.example.numberconvertor.repos.DictionaryRepo;
import com.example.numberconvertor.Convertor.Interfaces.IConvertNumber;
import org.springframework.beans.factory.annotation.Autowired;

public class ThousandAdd implements IConvertNumber {
    @Autowired
    DictionaryRepo dictionaryRepo;

    public String NumberToString(int number) {
        boolean isTeen = true;
        StringBuilder returnText = new StringBuilder();
        String numberString = String.valueOf(number);
        int numLenght = numberString.length();
        for (int i = numLenght; i > 0; i--) {
            int numberToConvert = Character.getNumericValue(numberString.charAt(numLenght - i));
            switch (i) {
                case 1:
                    if (isTeen) {
                        if (numberToConvert == 0) {
                            returnText.append(dictionaryRepo.findByIntnumberAndNumbertype(number, "addthousand2"));
                        }
                        if (numberToConvert == 1) {
                            returnText
                                    .append(dictionaryRepo.findByIntnumberAndNumbertype(number, "addfirst"))
                                    .append(" ")
                                    .append(dictionaryRepo.findByIntnumberAndNumbertype(number, "thousand"));
                        }
                        if (numberToConvert == 2) {
                            returnText
                                    .append(dictionaryRepo.findByIntnumberAndNumbertype(number, "addfirst"))
                                    .append(" ")
                                    .append(dictionaryRepo.findByIntnumberAndNumbertype(number, "addthousand"));
                        }
                        if (numberToConvert >= 3 && numberToConvert <= 4) {
                            returnText
                                    .append(new RuFirstsAdd().NumberToString(number))
                                    .append(" ")
                                    .append(dictionaryRepo.findByIntnumberAndNumbertype(number, "addthousand"));
                        }
                        if (numberToConvert >= 5) {
                            returnText
                                    .append(new RuFirstsAdd().NumberToString(numberToConvert))
                                    .append(" ")
                                    .append(dictionaryRepo.findByIntnumberAndNumbertype(number, "addthousand2"));
                        }
                        break;
                    }
                case 2:
                    int numberValue = Integer.parseInt(Character.toString(numberString.charAt(numLenght - 2)) + Character.toString(numberString.charAt(numLenght - 1)));
                    if (numberValue < 20 && numberValue >= 10) {
                        isTeen = false;
                        returnText.append(new RuTeensAdd().NumberToString(Character.getNumericValue(numberString.charAt(numLenght - 1))))
                                .append(" ")
                                .append(dictionaryRepo.findByIntnumberAndNumbertype(number, "addthousand2"));
                    } else {
                        returnText.append(new RuTensAdd().NumberToString(numberToConvert));
                    }
                    break;
                case 3:
                    returnText.append(new RuHundredsAdd().NumberToString(numberToConvert));
                    break;
            }

        }
        return returnText + " ";
    }
}

