package demo.design.level.low.parkinglot.services.parkinglot.core.services;

import demo.design.level.low.parkinglot.services.parkinglot.core.api.Card;
import demo.design.level.low.parkinglot.services.parkinglot.core.api.Cash;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.payments.Payment;

public interface PaymentService {

    Payment initiateTransaction();
    Payment performTransaction(Card card, Double amount);
    Payment performTransaction(Cash cash);

}
