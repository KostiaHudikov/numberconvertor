package ru.smsoft.numberconvertor.convertor.ru;

import ru.smsoft.numberconvertor.convertor.interfaces.ILanguageChoose;
import ru.smsoft.numberconvertor.convertor.ru.numbertotext.*;
import ru.smsoft.numberconvertor.repos.RuDictionaryRepos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class RuNumberConvertor implements ILanguageChoose {


    public String ConvertNumberToText(long number, JpaRepository DictionaryRepo) {
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
                    numberToConvert = Integer.parseInt(RangeChooser(numLenght, numberString, i));
                    context.SetConvertStrategy(new ThousandAdd());
                    break;
                case 7:
                    numberToConvert = Integer.parseInt(RangeChooser(numLenght, numberString, i));
                    context.SetConvertStrategy(new RuMillionAdd());
                    break;
                case 10:
                    numberToConvert = Integer.parseInt(RangeChooser(numLenght, numberString, i));
                    context.SetConvertStrategy(new RuBillionAdd());
                    break;
            }
            finalText.append(context.DoConvertToText(numberToConvert, DictionaryRepo));
        }
        return finalText.toString();
    }

    public long ConvertTextToNumber(String number, JpaRepository DictionaryRepo) {
        String[] numberList = number.split(" ");
        RuDictionaryRepos ruDictionaryRepo = (RuDictionaryRepos) DictionaryRepo;

        long finalNumber = 0;
        for (String textNumber : numberList) {
            boolean isMultiplication=false;
            long convertedNumber=ruDictionaryRepo.findByStringnumber(textNumber);

            if(convertedNumber == 1000 || convertedNumber == 1000000 ||
                    convertedNumber == 1000000000){
                isMultiplication= true;
            }

            if ( isMultiplication && finalNumber != 0) {
                finalNumber *= convertedNumber;
            } else {
                finalNumber += convertedNumber;
            }
        }
        return finalNumber;
    }

    private String RangeChooser(int numLenght, String numberString, int i) {
        String thousandNum = Character.toString(numberString.charAt(numLenght - i));
        if (numLenght >= i + 1) {
            thousandNum = Character.toString(numberString.charAt(numLenght - i - 1)) + thousandNum;
            if (numLenght >= i + 2) {
                thousandNum = Character.toString(numberString.charAt(numLenght - i - 2)) + thousandNum;
            }
        }
        return thousandNum;
    }
}

