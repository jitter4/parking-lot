package com.example.parkinglot.validations;

import com.example.parkinglot.beans.request.dto.LoginRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component(value="LoginRequestValidator")
public class LoginRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return LoginRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, LoginRequest._username, LoginRequest._usernameNullMessage);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, LoginRequest._password, LoginRequest._passwordNullMessage);
    }

}
