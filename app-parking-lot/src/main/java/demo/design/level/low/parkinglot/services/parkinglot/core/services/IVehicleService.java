package demo.design.level.low.parkinglot.services.parkinglot.core.services;

import demo.design.level.low.parkinglot.services.parkinglot.core.entities.Vehicle;
import org.springframework.stereotype.Service;

public interface IVehicleService {

    Vehicle save(Vehicle vehicle);
    Vehicle getByRegistrationNumber(String registrationNumber);

}
