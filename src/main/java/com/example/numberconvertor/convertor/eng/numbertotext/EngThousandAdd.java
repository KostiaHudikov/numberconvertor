package com.example.numberconvertor.convertor.eng.numbertotext;

import com.example.numberconvertor.convertor.interfaces.IConvertNumber;
import com.example.numberconvertor.repos.EngDictionaryRepos;
import org.springframework.data.jpa.repository.JpaRepository;

public class EngThousandAdd implements IConvertNumber {
    public String NumberToString(int number, JpaRepository DictionaryRepo) {
        EngDictionaryRepos engDictionaryRepo = (EngDictionaryRepos) DictionaryRepo;
        boolean isTeen = true;
        StringBuilder returnText = new StringBuilder();
        String numberString = String.valueOf(number);
        int numLenght = numberString.length();
        for (int i = String.valueOf(number).length(); i > 0; i--) {
            int numberToConvert = Character.getNumericValue(numberString.charAt(numLenght - i));
            switch (i) {
                case 1:
                    if (isTeen && numberToConvert != 0) {
                        returnText
                                .append(engDictionaryRepo.findByIntnumberAndNumbertype(numberToConvert, "first"))
                                .append(" ")
                                .append(engDictionaryRepo.findByIntnumberAndNumbertype(1, "thousand"));
                        break;
                    } else {
                        returnText.append(engDictionaryRepo.findByIntnumberAndNumbertype(1, "thousand"))
                                .append(" ");
                        break;
                    }
                case 2:
                    int numberValue = Integer.parseInt(Character.toString(numberString.charAt(numLenght - 2)) + Character.toString(numberString.charAt(numLenght - 1)));
                    if (numberValue == 0) {
                        break;
                    }
                    if (numberValue < 20 && numberValue >= 10) {
                        isTeen = false;
                        returnText
                                .append(new EngTeensAdd().NumberToString(Character.getNumericValue(numberString.charAt(numLenght - 1)), engDictionaryRepo))
                                .append(" ");
                    } else {
                        returnText
                                .append(new EngTensAdd().NumberToString(numberToConvert, engDictionaryRepo))
                                .append(" ");
                    }
                    break;
                case 3:
                    returnText
                            .append(new EngHundredAdd().NumberToString(numberToConvert, engDictionaryRepo))
                            .append(" ");
                    break;
                case 4:
                    returnText
                            .append(new EngFirstAdd().NumberToString(numberToConvert, engDictionaryRepo))
                            .append(" ");
                    break;
            }

        }
        return returnText.toString();
    }
}

