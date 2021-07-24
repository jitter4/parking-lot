package demo.design.level.low.parkinglot.services.authentication.controllers;

import demo.design.level.low.parkinglot.services.authentication.api.dto.requests.LoginRequest;
import demo.design.level.low.parkinglot.services.authentication.api.dto.requests.SignUpRequest;
import demo.design.level.low.parkinglot.api.dto.response.core.CoreResponse;
import demo.design.level.low.parkinglot.api.dto.response.core.SuccessResponse;
import demo.design.level.low.parkinglot.services.authentication.api.dto.responses.AuthenticatedUserResponse;
import demo.design.level.low.parkinglot.api.dto.response.util.ResponseUtils;
import demo.design.level.low.parkinglot.services.authentication.services.RoleService;
import demo.design.level.low.parkinglot.services.authentication.services.UserAuthenticationService;
import demo.design.level.low.parkinglot.services.authentication.services.UserService;
import demo.design.level.low.parkinglot.services.authentication.exceptions.AuthenticationException;
import demo.design.level.low.parkinglot.services.authentication.entities.Role;
import demo.design.level.low.parkinglot.services.authentication.entities.User;
import demo.design.level.low.parkinglot.services.authentication.enums.RoleName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@Slf4j
@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {

    private final static String succesfulAuthenticationMessage = "Login Successful";

    @Autowired
    private UserService userService;

    @Autowired
    private UserAuthenticationService authenticationService;

    @Autowired
    @Qualifier(value = "LoginRequestValidator")
    private Validator loginRequestValidator;

    @Autowired
    @Qualifier(value = "SignRequestValidator")
    private Validator signUpRequestValidator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @PostMapping("/login")
    public ResponseEntity<CoreResponse> authenticateUser(@RequestBody final LoginRequest loginRequest, final Errors errors,
                                                         final HttpServletRequest httpServletRequest) {

        this.loginRequestValidator.validate(loginRequest, errors);
        if (errors.hasErrors()) {
            return new ResponseEntity<>(
                    ResponseUtils.invalidRequstFieldResponse(
                            HttpStatus.BAD_REQUEST.getReasonPhrase(),
                            ResponseUtils.buildFieldErrors(errors)),
                    HttpStatus.BAD_REQUEST);
        }
        AuthenticatedUserResponse authenticatedUserResponse = this.authenticationService.validateUser(loginRequest, httpServletRequest);
        log.info("Login request for username:-{} is successful", loginRequest.getUsername());
        return new ResponseEntity<>(
                ResponseUtils.successResponse(
                        succesfulAuthenticationMessage,
                        HttpStatus.OK.value(),
                        authenticatedUserResponse),
                HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<CoreResponse> registerUser(@Valid @RequestBody final SignUpRequest signUpRequest,
                                                     final Errors errors) {

       this.signUpRequestValidator.validate(signUpRequest, errors);
       if (errors.hasErrors()) {
           return new ResponseEntity<>(
                   ResponseUtils.invalidRequstFieldResponse(
                           HttpStatus.BAD_REQUEST.getReasonPhrase(),
                           ResponseUtils.buildFieldErrors(errors)),
                   HttpStatus.BAD_REQUEST);
       }

       if (userService.existsByUsername(signUpRequest.getUsername())) {
           return new ResponseEntity<>(
                   ResponseUtils.invalidRequstFieldResponse(
                           "Username Already Taken",
                           ResponseUtils.buildFieldErrors(errors)),
                   HttpStatus.BAD_REQUEST);
       }

        if (userService.existsByEmail(signUpRequest.getUsername())) {
            return new ResponseEntity<>(
                    ResponseUtils.invalidRequstFieldResponse(
                            "Email Already Used By Another Account",
                            ResponseUtils.buildFieldErrors(errors)),
                    HttpStatus.BAD_REQUEST);
        }


        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                    signUpRequest.getPassword(), signUpRequest.getEmail());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleService.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AuthenticationException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        User result = userService.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new SuccessResponse("User registered successfully"));
    }

}

