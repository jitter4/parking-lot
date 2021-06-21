package demo.design.level.low.parkinglot.services.authentication.configurations.validations;

import demo.design.level.low.parkinglot.services.authentication.api.dto.requests.SignUpRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component(value="SignRequestValidator")
public class SignUpRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return SignUpRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, SignUpRequest._name,     SignUpRequest._nameNullMessage);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, SignUpRequest._username, SignUpRequest._usernameNullMessage);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, SignUpRequest._password, SignUpRequest._passwordNullMessage);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, SignUpRequest._email,    SignUpRequest._emailNullMessage);
    }

}
