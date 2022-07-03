package com.example.numberconvertor.controllers;


import com.example.numberconvertor.Convertor.RU.NumberToText.RuFirstsAdd;
import com.example.numberconvertor.repos.DictionaryRepo;
import com.example.numberconvertor.Convertor.LanguageChooser.LanguageChooseContext;
import com.example.numberconvertor.Convertor.RU.RuNumberConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Validated
public class MainController {

    @Autowired
    private LanguageChooseContext languageChoose;

    @Autowired
    DictionaryRepo dictionaryRepo;

    @Autowired
    RuFirstsAdd ruFirstsAdd;

    @GetMapping("/")
    public String main() {
        languageChoose.SetConvertToIntStrategy(new RuNumberConvertor());
        languageChoose.DoConvertNumberToText(8);
        return "login.html";
    }

    @GetMapping("/login")
    public String main2() {return "login.html";
    }

    @GetMapping("/Convert")
    public String main3() {

        return "login.html";
    }
}
