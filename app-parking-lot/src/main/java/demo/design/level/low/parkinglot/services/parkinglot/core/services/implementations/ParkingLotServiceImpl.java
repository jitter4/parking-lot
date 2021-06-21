package demo.design.level.low.parkinglot.services.parkinglot.core.services.implementations;

import demo.design.level.low.parkinglot.services.parkinglot.core.entities.VehicleType;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.panels.EntrancePanel;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.panels.ExitPanel;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingDisplayBoard;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingFloor;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingLot;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.actors.ParkingLotAdministrator;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.actors.ParkingLotAttendent;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingLotStatus;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingSpot;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingTicket;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.Vehicle;
import demo.design.level.low.parkinglot.services.parkinglot.core.repositories.ParkingLotRepository;
import demo.design.level.low.parkinglot.services.parkinglot.core.repositories.ParkingSpotRepository;
import demo.design.level.low.parkinglot.services.parkinglot.core.repositories.VehicleRepository;
import demo.design.level.low.parkinglot.services.parkinglot.core.services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    private final ParkingSpotRepository parkingSpotRepository;
    private final VehicleRepository vehicleRepository;
    private final ParkingLotRepository parkingLotRepository;

    @Autowired
    public ParkingLotServiceImpl(final ParkingSpotRepository parkingSpotRepository, final VehicleRepository vehicleRepository,
                                 final ParkingLotRepository parkingLotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingLotRepository = parkingLotRepository;
    }

    @Override
    public ParkingLot addParkingLot(ParkingLot parkingLot) {
        return null;
    }

    @Override
    public Collection<ParkingLot> addParkingLots(Collection<ParkingLot> parkingLots) {
        return null;
    }

    @Override
    public Collection<ParkingFloor> addParkingFloors(ParkingLot parkingLot, Collection<ParkingFloor> parkingFloors) {
        return null;
    }

    @Override
    public Collection<ParkingSpot> addParkingSpots(ParkingFloor parkingFloor, Collection<ParkingSpot> parkingSpots) {
        return null;
    }

    @Override
    public Collection<ParkingSpot> addParkingSpots(ParkingLot parkingLot, Collection<ParkingSpot> parkingSpots) {
        return null;
    }

    @Override
    public Collection<ParkingDisplayBoard> addParkingDisplayBoards(ParkingLot parkingLot, Collection<ParkingDisplayBoard> parkingDisplayBoard) {
        return null;
    }

    @Override
    public Collection<EntrancePanel> addEntrancePanels(ParkingLot parkingLot, Collection<EntrancePanel> entrancePanels) {
        return null;
    }

    @Override
    public Collection<ExitPanel> addExitPanels(ParkingLot parkingLot, Collection<ExitPanel> exitPanels) {
        return null;
    }

    @Override
    public Collection<ParkingLotAttendent> addParkingLotAttendets(ParkingLot parkingLot, Collection<ParkingLotAttendent> parkingLotAttendents) {
        return null;
    }

    @Override
    public Collection<ParkingLotAdministrator> addParkingLotAdministrators(ParkingLot parkingLot, Collection<ParkingLotAdministrator> parkingLotAdministrators) {
        return null;
    }

    @Override
    public ParkingLot updateStatus(ParkingLot parkingLot, ParkingLotStatus parkingLotStatus) {
        return null;
    }

    @Override
    public ParkingTicket park(Vehicle vehicle) {
        return null;
    }

    @Override
    public Vehicle unPark(ParkingTicket parkingTicket) {
        return null;
    }

    @Override
    public Vehicle getVehicleByRegistrationNumber(final String registrationNumber) {
        return this.vehicleRepository.findTop1ByRegistrationNumberOrderByInTimeDesc(registrationNumber);
    }

    @Override
    public Page<Vehicle> getVehiclesByColor(final String color, final PageRequest pageRequest) {
        return this.parkingSpotRepository.findByVehicleColor(color, pageRequest).map(ParkingSpot::getVehicle);
    }

    @Override
    public Page<Vehicle> getVehiclesByType(VehicleType type, PageRequest pr) {
        return this.parkingSpotRepository.findByVehicleType(type, pr).map(ParkingSpot::getVehicle);
    }

    @Override
    public Page<Vehicle> getVehiclesByDuration(final Date inTme, final Date outTime, final PageRequest pageRequest) {
        return this.vehicleRepository.getByDuration(inTme, outTime, pageRequest);
    }
}