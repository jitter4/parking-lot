package demo.design.level.low.parkinglot.services.parkinglot.core.services.implementations;

import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingDisplayBoard;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingFloor;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingLot;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingLotStatus;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingSpot;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.actors.ParkingLotAdministrator;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.actors.ParkingLotAttendent;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.panels.EntrancePanel;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.panels.ExitPanel;
import demo.design.level.low.parkinglot.services.parkinglot.core.services.IAdministratorService;
import demo.design.level.low.parkinglot.services.parkinglot.core.services.IParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AdministratorService implements IAdministratorService {

    private final IParkingLotService parkingLotService;

    @Autowired
    public AdministratorService(final IParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @Override
    public Collection<ParkingLot> addParkingLots(final Collection<ParkingLot> parkingLots) {
        return this.parkingLotService.addParkingLots(parkingLots);
    }

    @Override
    public ParkingLot updateParkingLotStatus(final ParkingLot parkingLot,
                                             final ParkingLotStatus parkingLotStatus) {
        return this.parkingLotService.updateStatus(parkingLot, parkingLotStatus);
    }

    @Override
    public Collection<ParkingFloor> addParkingFloors(final ParkingLot parkingLot,
                                                     final Collection<ParkingFloor> parkingFloors) {
        return this.parkingLotService.addParkingFloors(parkingLot, parkingFloors);
    }

    @Override
    public Collection<ParkingSpot> addParkingSpots(final ParkingFloor parkingFloor,
                                                   final Collection<ParkingSpot> parkingSpots) {
        return this.parkingLotService.addParkingSpots(parkingFloor, parkingSpots);
    }

    @Override
    public Collection<ParkingSpot> addParkingSpots(final ParkingLot parkingLot,
                                                   final Collection<ParkingSpot> parkingSpots) {
        return this.parkingLotService.addParkingSpots(parkingLot, parkingSpots);
    }

    @Override
    public Collection<ParkingDisplayBoard> addParkingDisplayBoard(final ParkingLot parkingLot,
                                                                  final Collection<ParkingDisplayBoard> parkingDisplayBoards) {
        return this.parkingLotService.addParkingDisplayBoards(parkingLot, parkingDisplayBoards);
    }

    @Override
    public Collection<EntrancePanel> addEntrancePanels(final ParkingLot parkingLot,
                                                       final Collection<EntrancePanel> entrancePanels) {
        return this.parkingLotService.addEntrancePanels(parkingLot, entrancePanels);
    }

    @Override
    public Collection<ExitPanel> addExitPanels(final ParkingLot parkingLot, final Collection<ExitPanel> exitPanels) {
        return this.parkingLotService.addExitPanels(parkingLot, exitPanels);
    }

    @Override
    public Collection<ParkingLotAttendent> addParkingLotAttendents(final ParkingLot parkingLot,
                                                                   final Collection<ParkingLotAttendent> parkingLotAttendents) {
        return this.parkingLotService.addParkingLotAttendets(parkingLot, parkingLotAttendents);
    }

    @Override
    public Collection<ParkingLotAdministrator> addParkingLotAdministrators(final ParkingLot parkingLot,
                                                                           final Collection<ParkingLotAdministrator> parkingLotAdministrators) {
        return this.parkingLotService.addParkingLotAdministrators(parkingLot, parkingLotAdministrators);
    }
}
