package com.example.parkinglot.exceptions.handlers;

import com.example.parkinglot.beans.response.core.CoreResponse;
import com.example.parkinglot.beans.response.util.ResponseUtils;
import com.example.parkinglot.constants.response.ResponseContants;
import com.example.parkinglot.exceptions.CustomParkingLotException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExeptionHandler {

    @ExceptionHandler(value = { BadCredentialsException.class })
    public ResponseEntity<CoreResponse> handleAuthenticationFaiulreException(
            final BadCredentialsException badCredentialsException) {
        return new ResponseEntity<>(
                ResponseUtils.errorResponse(badCredentialsException.getMessage(), HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { MissingServletRequestParameterException.class })
    public ResponseEntity<CoreResponse> handleMissingServletRequestParameterException(
            final MissingServletRequestParameterException missingParameterException) {
        return new ResponseEntity<>(
                ResponseUtils.errorResponse(
                        missingParameterException.getParameterName() + ResponseContants.MISSING_PARAMETER_MESSAGE,
                        HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { MethodArgumentTypeMismatchException.class })
    public ResponseEntity<CoreResponse> handleMethodArgumentTypeMismatchException(
            final MethodArgumentTypeMismatchException mismatchException) {
        return new ResponseEntity<>(
                ResponseUtils.errorResponse(mismatchException.getMessage(), HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { CustomParkingLotException.class })
    public ResponseEntity<CoreResponse> handleDMSCustomException(final CustomParkingLotException parkingLotCustomException) {
        return new ResponseEntity<>(
                ResponseUtils.errorResponse(parkingLotCustomException.getMessage(), parkingLotCustomException.getStatus()),
                HttpStatus.valueOf(parkingLotCustomException.getStatus()));
    }
}
