package demo.design.level.low.parkinglot.exceptions.handlers;

import demo.design.level.low.parkinglot.api.dto.response.core.CoreResponse;
import demo.design.level.low.parkinglot.api.dto.response.util.ResponseUtils;
import demo.design.level.low.parkinglot.services.authentication.constants.ResponseConstants;
import demo.design.level.low.parkinglot.services.parkinglot.core.exceptions.CustomParkingLotException;
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
                        missingParameterException.getParameterName() + ResponseConstants.MISSING_PARAMETER_MESSAGE,
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
