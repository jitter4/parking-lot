package demo.design.level.low.parkinglot.services.authentication.services;

import demo.design.level.low.parkinglot.services.authentication.api.dto.requests.LoginRequest;
import demo.design.level.low.parkinglot.services.authentication.api.dto.responses.AuthenticatedUserResponse;
import org.springframework.security.authentication.BadCredentialsException;

import javax.servlet.http.HttpServletRequest;

public interface UserAuthenticationService {

    AuthenticatedUserResponse validateUser(final LoginRequest requestEntity,
                                           final HttpServletRequest httpServletRequest)
            throws BadCredentialsException;

}
