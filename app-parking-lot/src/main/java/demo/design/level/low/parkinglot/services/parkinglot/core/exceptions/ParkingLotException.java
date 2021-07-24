package demo.design.level.low.parkinglot.services.parkinglot.core.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
public class ParkingLotException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String message;

    private Integer apiResponseStatus;

    private int status;

    private String info;

    private String exceptionName;


}
