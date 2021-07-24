package demo.design.level.low.parkinglot.api.dto.response.core;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvalidField {

    String field;
    String message;

}
