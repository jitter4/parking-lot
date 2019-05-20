package com.example.parkinglot.config.swagger;

import lombok.Data;

@Data
public class SwaggerResource {

    private String name;
    private String locations;
    private String web;
    private SwaggerLocation location;

}
