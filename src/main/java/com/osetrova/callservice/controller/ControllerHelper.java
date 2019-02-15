package com.osetrova.callservice.controller;

import com.osetrova.callservice.exception.CallServiceException;
import com.osetrova.callservice.validator.PhoneNumberValidator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ControllerHelper {

    private PhoneNumberValidator validator;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(validator);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, CallServiceException.class})
    public ResponseEntity<List<String>> handleError(MethodArgumentNotValidException exception) {
        List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();
        return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(allErrors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
    }
}
