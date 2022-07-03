package com.example.numberconvertor.Convertor.ENG.NumberToText;

import com.example.numberconvertor.Convertor.Interfaces.IConvertNumber;

public class EngThousandAdd implements IConvertNumber {
    public String NumberToString(int num) {
        /*EngDicrionaryContainer dictionary = new EngDicrionaryContainer();
        StringBuilder returnText = new StringBuilder();
        int tmp;
        String numberString = String.valueOf(num);
        int numLenght = numberString.length();
        boolean teenChecker = true;
        for (int i = String.valueOf(num).length(); i > 0; i--) {
            tmp = Convert.ToInt32(num.ToString()[num.ToString().Length - i].ToString());
            switch (i) {
                case 1
                    when(teenChecker):
                    returnText.append(new EngFirstAdd().NumberToString(tmp)).append(" ").append(dictionary.thousand);
                    break;
                case 2:
                    if (Convert.ToInt32(numberString[numLenght - 2].ToString() + numberString[numLenght - 1].ToString()) < 20 && Convert.ToInt32(numberString[numLenght - 2].ToString() + numberString[numLenght - 1].ToString()) >= 10) {
                        teenChecker = false;
                        returnText.append(new EngTeensAdd().NumberToString(Convert.ToInt32(numberString[numLenght - 1].ToString()))).append(" ").append(dictionary.thousand);
                    } else {
                        returnText.append(new EngTensAdd().NumberToString(tmp));
                    }
                    break;
                case 3:
                    returnText.append(new EngHundredAdd().NumberToString(tmp));
                    break;
                case 4:
                    returnText.append(new EngFirstAdd().NumberToString(tmp));
                    break;
            }

        }*/
        //return returnText + " ";
        return "";
    }
}

