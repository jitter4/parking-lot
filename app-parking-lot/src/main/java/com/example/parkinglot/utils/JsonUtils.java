package com.example.parkinglot.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class JsonUtils {

    public static ObjectMapper mapper = new ObjectMapper();

    public static <T> T serialize(String s, Class<T> clazz) {
        if (StringUtils.isBlank(s)) throw new RuntimeException("Parking Lot unavilable");
        try {
            return mapper.readValue(s, clazz);
        } catch (IOException e) {
            throw new RuntimeException("");
        }
    }

}
