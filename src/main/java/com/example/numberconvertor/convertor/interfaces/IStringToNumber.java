package com.example.numberconvertor.convertor.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface IStringToNumber {
    int StringToNumber(String textNumber, JpaRepository engDictionaryRepo);
}

