package com.example.numberconvertor.convertor.ru.numbertotext;

import com.example.numberconvertor.repos.RuDictionaryRepos;
import com.example.numberconvertor.convertor.interfaces.IConvertNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public class ThousandAdd implements IConvertNumber {

    public String NumberToString(int number, JpaRepository DictionaryRepo) {
        RuDictionaryRepos ruDictionaryRepo = (RuDictionaryRepos) DictionaryRepo;
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
                            returnText.append(ruDictionaryRepo.findByIntnumberAndNumbertype(1, "addthousand2"));
                            break;
                        }
                        if (numberToConvert == 1) {
                            returnText
                                    .append(ruDictionaryRepo.findByIntnumberAndNumbertype(1, "addfirst"))
                                    .append(" ")
                                    .append(ruDictionaryRepo.findByIntnumberAndNumbertype(1, "thousand"));
                            break;
                        }
                        if (numberToConvert == 2) {
                            returnText
                                    .append(ruDictionaryRepo.findByIntnumberAndNumbertype(1, "addfirst"))
                                    .append(" ")
                                    .append(ruDictionaryRepo.findByIntnumberAndNumbertype(1, "addthousand"));
                            break;
                        }
                        if (numberToConvert >= 3 && numberToConvert <= 4) {
                            returnText
                                    .append(new RuFirstsAdd().NumberToString(numberToConvert, ruDictionaryRepo))
                                    .append(" ")
                                    .append(ruDictionaryRepo.findByIntnumberAndNumbertype(1, "addthousand"));
                            break;
                        }
                        if (numberToConvert >= 5) {
                            returnText
                                    .append(new RuFirstsAdd().NumberToString(numberToConvert, ruDictionaryRepo))
                                    .append(" ")
                                    .append(ruDictionaryRepo.findByIntnumberAndNumbertype(1, "addthousand2"));
                            break;
                        }
                        break;
                    }
                case 2:
                    if (numberToConvert == 0) {
                        break;
                    }
                    int numberValue = Integer.parseInt(Character.toString(numberString.charAt(numLenght - 2)) + Character.toString(numberString.charAt(numLenght - 1)));
                    if (numberValue < 20 && numberValue >= 10) {
                        isTeen = false;
                        returnText.append(new RuTeensAdd().NumberToString(Character.getNumericValue(numberString.charAt(numLenght - 1)), ruDictionaryRepo))
                                .append(" ")
                                .append(ruDictionaryRepo.findByIntnumberAndNumbertype(1, "addthousand2"));
                    } else {
                        returnText.append(new RuTensAdd().NumberToString(numberToConvert, ruDictionaryRepo))
                                .append(" ");
                    }
                    break;
                case 3:
                    returnText.append(new RuHundredsAdd().NumberToString(numberToConvert, ruDictionaryRepo))
                            .append(" ");
                    break;
            }

        }
        return returnText.toString();
    }
}

