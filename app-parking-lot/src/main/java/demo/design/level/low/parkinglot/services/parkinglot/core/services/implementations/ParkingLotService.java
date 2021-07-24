package demo.design.level.low.parkinglot.services.parkinglot.core.services.implementations;

import demo.design.level.low.parkinglot.exceptions.BadRequestException;
import demo.design.level.low.parkinglot.services.cache.ICacheService;
import demo.design.level.low.parkinglot.services.parkinglot.core.constants.enums.ParkingStatus;
import demo.design.level.low.parkinglot.services.parkinglot.core.constants.enums.ParkingTicketStatus;
import demo.design.level.low.parkinglot.services.parkinglot.core.constants.enums.VehicleStatus;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingDisplayBoard;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingFloor;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingLot;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingLotStatus;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingSpot;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingTicket;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.Vehicle;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.VehicleType;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.VehicleTypeParkingSpotCount;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.actors.ParkingLotAdministrator;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.actors.ParkingLotAttendent;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.panels.EntrancePanel;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.panels.ExitPanel;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.sequencegenerators.ParkingLotSequence;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.sequencegenerators.ParkingTicketSequence;
import demo.design.level.low.parkinglot.services.parkinglot.core.repositories.ParkingLotRepository;
import demo.design.level.low.parkinglot.services.parkinglot.core.repositories.ParkingLotSequenceRepository;
import demo.design.level.low.parkinglot.services.parkinglot.core.repositories.ParkingSpotRepository;
import demo.design.level.low.parkinglot.services.parkinglot.core.repositories.ParkingTicketRepository;
import demo.design.level.low.parkinglot.services.parkinglot.core.repositories.ParkingTicketSequenceRepository;
import demo.design.level.low.parkinglot.services.parkinglot.core.repositories.VehicleRepository;
import demo.design.level.low.parkinglot.services.parkinglot.core.services.IParkingLotService;
import demo.design.level.low.parkinglot.services.parkinglot.core.services.IParkingSpotStatusService;
import demo.design.level.low.parkinglot.services.parkinglot.core.services.IParkingTicketService;
import demo.design.level.low.parkinglot.services.parkinglot.core.services.IParkingTicketStatusService;
import demo.design.level.low.parkinglot.services.parkinglot.core.services.IVehicleParkingStatusService;
import demo.design.level.low.parkinglot.services.parkinglot.core.services.IVehicleService;
import demo.design.level.low.parkinglot.services.parkinglot.core.services.IVehicleTypeParkingSpotCountService;
import demo.design.level.low.parkinglot.services.parkinglot.core.services.locks.ILockService;
import demo.design.level.low.parkinglot.services.parkinglot.core.services.locks.implementations.LockService;
import demo.design.level.low.parkinglot.services.parkinglot.utils.StringUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class ParkingLotService implements IParkingLotService {
    private final ParkingSpotRepository parkingSpotRepository;
    private final VehicleRepository vehicleRepository;
    private final ParkingTicketRepository parkingTicketRepository;
    private final ParkingLotRepository parkingLotRepository;
    private final ICacheService cacheService;
    private final IVehicleTypeParkingSpotCountService vehicleTypeParkingSpotCountService;
    private final IParkingSpotStatusService parkingSpotStatusService;
    private final IVehicleService vehicleService;
    private final IVehicleParkingStatusService vehicleParkingStatusService;
    private final IParkingTicketStatusService parkingTicketStatusService;
    private final IParkingTicketService parkingTicketService;
    private final ParkingTicketSequenceRepository parkingTicketSequenceRepository;
    private final ParkingLotSequenceRepository parkingLotSequenceRepository;

    private final ILockService<String, ReentrantLock> vehicleRegistrationNumberLocks = new LockService<>(ReentrantLock.class);

    @Autowired
    public ParkingLotService(final ParkingSpotRepository parkingSpotRepository,
                             final VehicleRepository vehicleRepository,
                             final ParkingTicketRepository parkingTicketRepository,
                             final ParkingLotRepository parkingLotRepository,
                             final ICacheService cacheService,
                             final IVehicleTypeParkingSpotCountService vehicleTypeParkingSpotCountService,
                             final IParkingSpotStatusService parkingSpotStatusService,
                             final IVehicleService vehicleService,
                             final IVehicleParkingStatusService vehicleParkingStatusService,
                             final IParkingTicketStatusService parkingTicketStatusService,
                             final IParkingTicketService parkingTicketService,
                             final ParkingTicketSequenceRepository parkingTicketSequenceRepository,
                             final ParkingLotSequenceRepository parkingLotSequenceRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingTicketRepository = parkingTicketRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.cacheService = cacheService;
        this.vehicleTypeParkingSpotCountService = vehicleTypeParkingSpotCountService;
        this.parkingSpotStatusService = parkingSpotStatusService;
        this.vehicleService = vehicleService;
        this.vehicleParkingStatusService = vehicleParkingStatusService;
        this.parkingTicketStatusService = parkingTicketStatusService;
        this.parkingTicketService = parkingTicketService;
        this.parkingTicketSequenceRepository = parkingTicketSequenceRepository;
        this.parkingLotSequenceRepository = parkingLotSequenceRepository;
    }

    @Transactional
    @Override
    public ParkingTicket park(final ParkingLot parkingLotVO, final Vehicle vehicleVO) {
        ParkingTicket parkingTicket = null;
        ParkingLot parkingLot = this.parkingLotRepository.findByCode(parkingLotVO.getCode());
        if (parkingLot == null) {
            throw new BadRequestException("Invalid parking lot name");
        }
        var lock = this.vehicleRegistrationNumberLocks.getLock(vehicleVO.getRegistrationNumber());
        try {
            lock.lock();
            var vehicle = this.vehicleService.getByRegistrationNumber(vehicleVO.getRegistrationNumber());
            if (vehicle != null) {
                vehicle.setColor(vehicleVO.getColor());
                vehicle.setType(vehicleVO.getType());
            } else {
                vehicle = vehicleVO;
            }
            if (vehicle.getParkingStatus().getName().equals(ParkingStatus.PARKED.name())) {
                throw new BadRequestException("Vehicle already parked");
            }

            var vehicleTypeName = vehicle.getType().getName();
            var vehicleTypeParkingSpotCounts
                    = this.vehicleTypeParkingSpotCountService.getByParkingLotAndVehicleTypeName(parkingLot, vehicle.getType().getName());
            if (CollectionUtils.isEmpty(vehicleTypeParkingSpotCounts)) {
                throw new BadRequestException(String.format("Vehicle Type: %s, cannot be parked.", vehicle.getType().getName()));
            }

            for (final VehicleTypeParkingSpotCount vehicleTypeParkingSpotCount : vehicleTypeParkingSpotCounts) {
                var parkingSpotType = vehicleTypeParkingSpotCount.getParkingSpotType();
                var count = vehicleTypeParkingSpotCount.getCount();

                synchronized ((parkingSpotType.getName() + "----" + parkingLot.getId()).intern()) {
                    var parkingSpots = this.parkingSpotRepository
                            .findByParkingLotIdAndParkingSpotTypeNameAndAndParkingSpotStatusNameOrderByNumberAscParkingFloorNumberAsc(
                                    parkingLot.getId(), parkingSpotType.getName(), ParkingStatus.EMPTY.name(),
                                    PageRequest.of(0, count)
                            );
                    if (CollectionUtils.isEmpty(parkingSpots) || parkingSpots.size() < count) {
                        continue;
                    }

                    var parkedParkingSpotStatus = this.parkingSpotStatusService.getByName(ParkingStatus.PARKED.name());
                    vehicle.setParkingSpots(new ArrayList<>());
                    for (ParkingSpot parkingSpot : parkingSpots) {
                        parkingSpot.setParkingSpotStatus(parkedParkingSpotStatus);
                        vehicle.getParkingSpots().add(parkingSpot);
                    }
                    var vehicleParkingStatus = this.vehicleParkingStatusService.getByName(VehicleStatus.PARKED.name());
                    vehicle.setParkingStatus(vehicleParkingStatus);
                    var parkingTicketStatus = this.parkingTicketStatusService.getByName(ParkingTicketStatus.UNPAID_PARKED.name());
                    var parkingTicketSequence = this.parkingTicketSequenceRepository.save(ParkingTicketSequence.builder().build());
                    final var ticketNumber = new SimpleDateFormat("yyyyMM").format(new Date()) + parkingLot.getCode() + parkingTicketSequence.getId();
                    parkingTicket = new ParkingTicket(ticketNumber, vehicle, new Date(), null,
                            vehicle.getParkingSpots(), parkingTicketStatus, parkingLot);
                    this.parkingTicketService.save(parkingTicket);
                }
            }
        } finally {
            lock.unlock();
        }
        return parkingTicket;
    }

    @Override
    public Vehicle unPark(final ParkingTicket parkingTicketVO) {
        var parkingTicket = this.parkingTicketService.findByNumber(parkingTicketVO.getNumber());
        if (parkingTicket == null) {
            throw new BadRequestException("Invalid parking ticket");
        }
        var lock = this.vehicleRegistrationNumberLocks.getLock(parkingTicket.getVehicle().getRegistrationNumber());
        try {
            lock.lock();
            var parkingSpotStatus= this.parkingSpotStatusService.getByName(ParkingStatus.EMPTY.name());
            parkingTicket.getVehicle().getParkingSpots().forEach(parkingSpot -> parkingSpot.setParkingSpotStatus(parkingSpotStatus));
            var vehicleParkingStatus = this.vehicleParkingStatusService.getByName(ParkingStatus.UNPARKED.name());
            parkingTicket.getVehicle().setParkingStatus(vehicleParkingStatus);
            var parkingTicketStatus= this.parkingTicketStatusService.getByName(ParkingTicketStatus.UNPAID.name());
            parkingTicket.setParkingTicketStatus(parkingTicketStatus);
            parkingTicket.setOutTime(new Date());
            parkingTicket = this.parkingTicketService.save(parkingTicket);
        } finally {
            lock.unlock();
        }
        return parkingTicket.getVehicle();
    }

    @Override
    public ParkingLot addParkingLot(final ParkingLot parkingLot) {
        final var parkingLotCode = "PL" + new Date().getYear()
                + StringUtils.getFirstCharacterFromEachWordUpperCase(parkingLot.getName())
                + this.parkingLotSequenceRepository.save(ParkingLotSequence.builder().build());
        parkingLot.setCode(parkingLotCode);
        return this.parkingLotRepository.save(parkingLot);
    }

    @Override
    public Collection<ParkingLot> addParkingLots(Collection<ParkingLot> parkingLots) {
        return this.parkingLotRepository.saveAll(parkingLots);
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
    public Vehicle getVehicleByRegistrationNumber(final String registrationNumber) {
        return this.vehicleRepository.findTop1ByRegistrationNumber(registrationNumber);
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
        return this.parkingTicketRepository.getByDuration(inTme, outTime, pageRequest).map(ParkingTicket::getVehicle);
    }
}