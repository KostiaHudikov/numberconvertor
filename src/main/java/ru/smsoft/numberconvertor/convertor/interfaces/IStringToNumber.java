package ru.smsoft.numberconvertor.convertor.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface IStringToNumber {
    long StringToNumber(String textNumber, JpaRepository engDictionaryRepo);
}

