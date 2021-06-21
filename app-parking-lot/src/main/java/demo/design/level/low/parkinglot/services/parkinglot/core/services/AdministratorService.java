package demo.design.level.low.parkinglot.services.parkinglot.core.services;

import demo.design.level.low.parkinglot.services.parkinglot.core.entities.actors.ParkingLotAdministrator;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.actors.ParkingLotAttendent;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingLotStatus;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.panels.EntrancePanel;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.panels.ExitPanel;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingDisplayBoard;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingFloor;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingLot;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingSpot;

import java.util.Collection;

public interface AdministratorService {

    Collection<ParkingLot> addParkingLots(Collection<ParkingLot> parkingLots);
    ParkingLot updateParkingLotStatus(ParkingLot parkingLot, ParkingLotStatus status);

    Collection<ParkingFloor> addParkingFloors(ParkingLot parkingLot, Collection<ParkingFloor> parkingFloors);
    Collection<ParkingSpot> addParkingSpots(ParkingFloor parkingFloor, Collection<ParkingSpot> parkingSpots);
    Collection<ParkingSpot> addParkingSpots(ParkingLot parkingLot, Collection<ParkingSpot> parkingSpots);

    Collection<ParkingDisplayBoard> addParkingDisplayBoard(ParkingLot parkingLot, Collection<ParkingDisplayBoard> displayBoards);
    Collection<EntrancePanel> addEntrancePanels(ParkingLot parkingLot, Collection<EntrancePanel> entrancePanels);
    Collection<ExitPanel> addExitPanels(ParkingLot parkingLot, Collection<ExitPanel> exitPanels);

    Collection<ParkingLotAttendent> addParkingLotAttendents(ParkingLot parkingLot, Collection<ParkingLotAttendent> parkingLotAttendents);
    Collection<ParkingLotAdministrator> addParkingLotAdministrators(ParkingLot parkingLot, Collection<ParkingLotAdministrator> parkingLotAdministrators);
    
}
