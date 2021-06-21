package demo.design.level.low.parkinglot.services.parkinglot.core.services;

import demo.design.level.low.parkinglot.services.parkinglot.core.api.Cash;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingTicket;

public interface ParkingLotAttendentService {

    ParkingTicket process(ParkingTicket parkingTicket);
    ParkingTicket acceptCash(ParkingTicket parkingTicket, Cash cash);

}
