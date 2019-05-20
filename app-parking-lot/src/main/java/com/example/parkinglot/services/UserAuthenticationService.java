package com.example.parkinglot.services;

import com.example.parkinglot.beans.request.dto.LoginRequest;
import com.example.parkinglot.beans.response.dto.AuthenticatedUserResponse;
import org.springframework.security.authentication.BadCredentialsException;

import javax.servlet.http.HttpServletRequest;

public interface UserAuthenticationService {

    AuthenticatedUserResponse validateUser(final LoginRequest requestEntity, final HttpServletRequest httpServletRequest)
            throws BadCredentialsException;

}
