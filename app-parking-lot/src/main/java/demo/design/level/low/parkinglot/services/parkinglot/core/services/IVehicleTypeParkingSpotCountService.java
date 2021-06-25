package demo.design.level.low.parkinglot.services.parkinglot.core.services;

import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingLot;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.VehicleTypeParkingSpotCount;

import java.util.Collection;
import java.util.List;

public interface IVehicleTypeParkingSpotCountService {

    Collection<VehicleTypeParkingSpotCount> getByParkingLotAndVehicleTypeName(ParkingLot parkingLot, String vehicleTypeName);

}
