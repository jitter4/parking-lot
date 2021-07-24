package demo.design.level.low.parkinglot.services.authentication.configurations.swagger;

import lombok.Data;

@Data
public class Headers {

    private String jti;
    private String uti;
    private String authorization;

}
