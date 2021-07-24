package demo.design.level.low.parkinglot.services.parkinglot.core.api;

import lombok.Data;

import java.util.Date;

@Data
public class Card {

    private String name;
    private String cardNumber;
    private int expiryMonth;
    private int expiryYear;

}
