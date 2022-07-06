package ru.smsoft.numberconvertor.convertor.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface IConvertNumber {
    String NumberToString(long num, JpaRepository engDictionaryRepo);
}
