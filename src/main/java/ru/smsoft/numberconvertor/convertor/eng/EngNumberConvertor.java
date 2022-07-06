package ru.smsoft.numberconvertor.convertor.eng;

import ru.smsoft.numberconvertor.convertor.eng.numbertotext.*;
import ru.smsoft.numberconvertor.convertor.interfaces.ILanguageChoose;
import ru.smsoft.numberconvertor.repos.EngDictionaryRepos;
import org.springframework.data.jpa.repository.JpaRepository;

public class EngNumberConvertor implements ILanguageChoose {

    public String ConvertNumberToText(long number, JpaRepository DictionaryRepo) {
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
                    numberToConvert = Integer.parseInt(RangeChooser(numLenght, numberString, i));
                    context.SetConvertStrategy(new EngThousandAdd());
                    break;
                case 7:
                    numberToConvert = Integer.parseInt(RangeChooser(numLenght, numberString, i));
                    context.SetConvertStrategy(new EngMillionAdd());
                    break;
                case 10:
                    numberToConvert = Integer.parseInt(RangeChooser(numLenght, numberString, i));
                    context.SetConvertStrategy(new EngBillionAdd());
                    break;
            }
            finalText.append(context.DoConvertToText(numberToConvert, DictionaryRepo));
        }
        return finalText.toString();
    }

    public long ConvertTextToNumber(String number, JpaRepository DictionaryRepo) {
        String[] numberList = number.split(" ");
        long finalNumber = 0;
        long result = 0;
        int degree = 1;

        for (String textNumber : numberList) {
            EngDictionaryRepos engDictionaryRepo = (EngDictionaryRepos) DictionaryRepo;
            long convertedNumber = engDictionaryRepo.findByStringnumber(textNumber);

            if (convertedNumber == 100) {
                result *= convertedNumber;
            } else if (engDictionaryRepo.findByStringnumber(textNumber) == 1000 || engDictionaryRepo.findByStringnumber(textNumber) == 1000000 ||
                    engDictionaryRepo.findByStringnumber(textNumber) == 1000000000) {
                result *= engDictionaryRepo.findByStringnumber(textNumber);
                finalNumber += result;
                result = 0;
                degree++;
            } else {
                result += convertedNumber;
            }
        }
        return finalNumber + result;
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

