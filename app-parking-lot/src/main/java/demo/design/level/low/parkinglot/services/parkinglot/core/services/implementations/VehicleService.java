package demo.design.level.low.parkinglot.services.parkinglot.core.services.implementations;

import demo.design.level.low.parkinglot.services.parkinglot.core.entities.Vehicle;
import demo.design.level.low.parkinglot.services.parkinglot.core.repositories.VehicleRepository;
import demo.design.level.low.parkinglot.services.parkinglot.core.services.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService implements IVehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(final VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Vehicle save(final Vehicle vehicle) {
        return this.vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle getByRegistrationNumber(final String registrationNumber) {
        return this.vehicleRepository.findByRegistrationNumber(registrationNumber);
    }
}
