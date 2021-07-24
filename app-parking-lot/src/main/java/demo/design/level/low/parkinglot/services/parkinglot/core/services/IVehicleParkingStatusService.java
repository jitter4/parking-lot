package demo.design.level.low.parkinglot.services.parkinglot.core.services;

import demo.design.level.low.parkinglot.services.parkinglot.core.entities.VehicleParkingStatus;

public interface IVehicleParkingStatusService {

    VehicleParkingStatus getByName(String name);

}
