package ru.smsoft.numberconvertor.exeption;

import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;

@RestControllerAdvice
public class HttpClientErrorExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity handleHttpClientError(HttpClientErrorException e) {
        val responseMessage = new HashMap<String, Object>();
        responseMessage.put("message", e.getStatusText());
        return new ResponseEntity(responseMessage, e.getStatusCode());
    }
}
