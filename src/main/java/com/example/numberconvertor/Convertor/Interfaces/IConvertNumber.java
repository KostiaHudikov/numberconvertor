package com.example.numberconvertor.Convertor.Interfaces;

import com.example.numberconvertor.repos.DictionaryRepo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public interface IConvertNumber {
    String NumberToString(int num);
}
