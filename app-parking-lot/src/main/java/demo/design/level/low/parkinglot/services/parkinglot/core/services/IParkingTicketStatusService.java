package demo.design.level.low.parkinglot.services.parkinglot.core.services;

import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingTicketStatus;

public interface IParkingTicketStatusService {

    ParkingTicketStatus getByName(String name);

}
