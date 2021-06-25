package demo.design.level.low.parkinglot.services.parkinglot.core.services;

import demo.design.level.low.parkinglot.services.parkinglot.core.entities.VehicleTypeParkingSpotCount;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.actors.ParkingLotAdministrator;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.panels.EntrancePanel;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.panels.ExitPanel;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.actors.ParkingLotAttendent;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingDisplayBoard;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingFloor;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingLotStatus;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingTicket;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.Vehicle;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.VehicleType;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingLot;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingSpot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface IParkingLotService {

    ParkingTicket park(ParkingLot parkingLot, Vehicle vehicle);
    Vehicle unPark(ParkingTicket parkingTicket);

    ParkingLot addParkingLot(ParkingLot parkingLot);
    Collection<ParkingLot> addParkingLots(Collection<ParkingLot> parkingLots);
    Collection<ParkingFloor> addParkingFloors(ParkingLot parkingLot, Collection<ParkingFloor> parkingFloors);
    Collection<ParkingSpot> addParkingSpots(ParkingFloor parkingFloor, Collection<ParkingSpot> parkingSpots);
    Collection<ParkingSpot> addParkingSpots(ParkingLot parkingLot, Collection<ParkingSpot> parkingSpots);

    Collection<ParkingDisplayBoard> addParkingDisplayBoards(ParkingLot parkingLot, Collection<ParkingDisplayBoard> parkingDisplayBoard);
    Collection<EntrancePanel> addEntrancePanels(ParkingLot parkingLot, Collection<EntrancePanel> entrancePanels);
    Collection<ExitPanel> addExitPanels(ParkingLot parkingLot, Collection<ExitPanel> exitPanels);

    Collection<ParkingLotAttendent> addParkingLotAttendets(ParkingLot parkingLot, Collection<ParkingLotAttendent> parkingLotAttendents);
    Collection<ParkingLotAdministrator> addParkingLotAdministrators(ParkingLot parkingLot, Collection<ParkingLotAdministrator> parkingLotAdministrators);

    ParkingLot updateStatus(ParkingLot parkingLot, ParkingLotStatus parkingLotStatus);

    Vehicle getVehicleByRegistrationNumber(String registrationNumber);
    Page<Vehicle> getVehiclesByColor(String color, PageRequest pageRequest);
    Page<Vehicle> getVehiclesByType(VehicleType color, PageRequest pageRequest);
    Page<Vehicle> getVehiclesByDuration(Date startTime, Date endTime, PageRequest pageRequest);
}
