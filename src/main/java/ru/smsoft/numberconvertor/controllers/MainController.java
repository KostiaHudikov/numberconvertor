package ru.smsoft.numberconvertor.controllers;

import ru.smsoft.numberconvertor.convertor.eng.EngNumberConvertor;
import ru.smsoft.numberconvertor.postgress.logs.Logs;
import ru.smsoft.numberconvertor.repos.EngDictionaryRepos;
import ru.smsoft.numberconvertor.repos.LogsRepos;
import ru.smsoft.numberconvertor.repos.RuDictionaryRepos;
import ru.smsoft.numberconvertor.convertor.languagechooser.LanguageChooseContext;
import ru.smsoft.numberconvertor.convertor.ru.RuNumberConvertor;
import lombok.RequiredArgsConstructor;
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

        if (dictionaryRepo == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "not a valid language");
        }


        String outer_val = "";
        if (type.equals("NumberToString")) {
            try {
                outer_val = languageChoose.DoConvertNumberToText(Long.parseLong(value), dictionaryRepo);
            } catch (RuntimeException e) {
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "invalid input number");
            }
        } else if (type.equals("StringToNumber")) {
            try {
                outer_val = String.valueOf(languageChoose.DoConvertTextToNumber(value, dictionaryRepo));
            } catch (RuntimeException e) {
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "invalid input number");
            }
        } else {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "invalid conversion type");
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
