package ru.biryuchev.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.biryuchev.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.biryuchev.MySecondTestAppSpringBoot.exception.ValidationFailedException;


@Service
public interface ValidationService {

    void isValid(BindingResult bindingResult) throws ValidationFailedException, UnsupportedCodeException;
}