package demo.design.level.low.parkinglot.services.authentication.configurations.validations;

import demo.design.level.low.parkinglot.services.authentication.api.dto.requests.LoginRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component(value = "LoginRequestValidator")
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
