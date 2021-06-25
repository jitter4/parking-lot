package demo.design.level.low.parkinglot.services.parkinglot.core.services;

import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingSpotStatus;

public interface IParkingSpotStatusService {

    ParkingSpotStatus getByName(String name);

}
