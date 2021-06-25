package demo.design.level.low.parkinglot.services.parkinglot.core.services;

import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingTicket;

public interface IParkingTicketService {

    ParkingTicket save(ParkingTicket ticket);
    ParkingTicket findByNumber(String number);

}
