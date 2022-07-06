package com.example.numberconvertor.convertor.ru;

import com.example.numberconvertor.convertor.interfaces.ILanguageChoose;
import com.example.numberconvertor.convertor.ru.numbertotext.*;
import com.example.numberconvertor.repos.RuDictionaryRepos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class RuNumberConvertor implements ILanguageChoose {


    public String ConvertNumberToText(int number, JpaRepository DictionaryRepo) {
        boolean isTeen = true;
        String numberString = String.valueOf(number);
        int numLenght = numberString.length();
        String text = "";
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
            }
            finalText.append(context.DoConvertToText(numberToConvert, DictionaryRepo));
        }
        return finalText.toString();
    }

    public int ConvertTextToNumber(String number, JpaRepository DictionaryRepo) {
        String[] numberList = number.split(" ");
        int finalNumber = 0;
        for (String textNumber : numberList) {
            RuDictionaryRepos ruDictionaryRepo = (RuDictionaryRepos) DictionaryRepo;

            if (ruDictionaryRepo.findByStringnumber(textNumber) != 1000 || finalNumber == 0) {
                finalNumber += ruDictionaryRepo.findByStringnumber(textNumber);
            } else {
                finalNumber *= ruDictionaryRepo.findByStringnumber(textNumber);
            }
        }
        return finalNumber;
    }
}

