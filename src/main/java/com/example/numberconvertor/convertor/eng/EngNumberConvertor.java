package com.example.numberconvertor.convertor.eng;

import com.example.numberconvertor.convertor.eng.numbertotext.*;
import com.example.numberconvertor.convertor.interfaces.ILanguageChoose;
import com.example.numberconvertor.repos.EngDictionaryRepos;
import org.springframework.data.jpa.repository.JpaRepository;

public class EngNumberConvertor implements ILanguageChoose {

    public String ConvertNumberToText(int number, JpaRepository DictionaryRepo) {
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
                        }
                    }
                    numberToConvert = Integer.parseInt(thousandNum);
                    context.SetConvertStrategy(new EngThousandAdd());
                    break;
            }
            finalText.append(context.DoConvertToText(numberToConvert, DictionaryRepo));
        }
        return finalText.toString();
    }

    public int ConvertTextToNumber(String number, JpaRepository DictionaryRepo) {
        String[] numberList = number.split(" ");
        int finalNumber = 0;
        int result = 0;


        for (String textNumber : numberList) {
            EngDictionaryRepos engDictionaryRepo = (EngDictionaryRepos) DictionaryRepo;
            int convertedNumber = engDictionaryRepo.findByStringnumber(textNumber);

            if (convertedNumber == 100) {
                result *= convertedNumber;
            } else if (convertedNumber == 1000) {
                result *= 1000;
                finalNumber += result;
                result = 0;
            } else {
                result += convertedNumber;
            }
        }
        return finalNumber + result;
    }
}

