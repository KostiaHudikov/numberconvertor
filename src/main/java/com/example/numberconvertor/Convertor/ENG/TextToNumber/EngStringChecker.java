package com.example.numberconvertor.Convertor.ENG.TextToNumber;

public class EngStringChecker {
    public boolean FirstChecker(String textNumber)
    {

       /* EngDicrionaryContainer dictionary = new EngDicrionaryContainer();
        foreach (var i in dictionary.firsts)
        {
            if (i.Value == textNumber)
            {
                return true;
            }
        }*/
        return false;

    }
    public boolean TeenChecker(String textNumber)
    {
/*        EngDicrionaryContainer dictionary = new EngDicrionaryContainer();
        foreach (var i in dictionary.teens)
        {
            if (i.Value == textNumber)
            {
                return true;
            }
        }*/
        return false;
    }
    public boolean TenChecker(String textNumber)
    {
/*        EngDicrionaryContainer dictionary = new EngDicrionaryContainer();
        foreach (var i in dictionary.tens)
        {
            if (i.Value == textNumber)
            {
                return true;
            }
        }*/
        return false;
    }
    public boolean HundredChecker(String textNumber)
    {
/*        EngDicrionaryContainer dictionary = new EngDicrionaryContainer();
        if (textNumber == dictionary.hundred)
        {
            return true;
        }*/
        return false;
    }
    public boolean ThousendChecker(String textNumber)
    {
/*        EngDicrionaryContainer dictionary = new EngDicrionaryContainer();
        if (textNumber == dictionary.thousand)
        {
            return true;
        }*/
        return false;
    }

}
