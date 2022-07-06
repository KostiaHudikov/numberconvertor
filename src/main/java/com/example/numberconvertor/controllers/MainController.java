package com.example.numberconvertor.controllers;


import com.example.numberconvertor.convertor.eng.EngNumberConvertor;
import com.example.numberconvertor.domain.Logs;
import com.example.numberconvertor.repos.EngDictionaryRepos;
import com.example.numberconvertor.repos.LogsRepos;
import com.example.numberconvertor.repos.RuDictionaryRepos;
import com.example.numberconvertor.convertor.languagechooser.LanguageChooseContext;
import com.example.numberconvertor.convertor.ru.RuNumberConvertor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.security.Principal;
import java.util.Date;

@RestController
@RequiredArgsConstructor

public class MainController {

    private final LogsRepos logsRepos;

    private final RuDictionaryRepos ruDictionaryRepo;

    private final EngDictionaryRepos engDictionaryRepo;

    private final LanguageChooseContext languageChoose;

    @GetMapping("/convert")
    public String main(@RequestParam(name = "type") String type,
                       @RequestParam(name = "value") String value,
                       @RequestParam(name = "language", required = false) String lang,
                       Principal principal) {
        JpaRepository dictionaryRepo = null;

        //Set language
        if (lang.equals("") || lang.equals("ru")) {
            languageChoose.SetConvertToIntStrategy(new RuNumberConvertor());
            dictionaryRepo = ruDictionaryRepo;
        }
        if (lang.equals("eng")) {
            languageChoose.SetConvertToIntStrategy(new EngNumberConvertor());
            dictionaryRepo = engDictionaryRepo;
        }

        if(dictionaryRepo == null){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Need choose language ru or eng");
        }


        String outer_val = "";
        if (type.equals("NumberToString")) {
            outer_val = languageChoose.DoConvertNumberToText(Integer.parseInt(value), dictionaryRepo);
        }
        if (type.equals("StringToNumber")) {
            outer_val = String.valueOf(languageChoose.DoConvertTextToNumber(value, dictionaryRepo));
        }

        Date date = new Date();
        logsRepos.save(Logs.builder()
                .login(principal.getName())
                .type(type).innerVal(value)
                .outerVal(outer_val)
                .date(date)
                .build());

        return outer_val;
    }
}
