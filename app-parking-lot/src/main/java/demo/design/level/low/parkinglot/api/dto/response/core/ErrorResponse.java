package demo.design.level.low.parkinglot.api.dto.response.core;

public class ErrorResponse extends CoreResponse {

    public ErrorResponse(String msg, int status) {
        super(msg, status);
    }

}
