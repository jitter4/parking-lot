package demo.design.level.low.parkinglot.services.authentication.configurations.swagger;

import lombok.Data;

@Data
public class SwaggerResource {

    private String name;
    private String locations;
    private String web;
    private SwaggerLocation location;

}
