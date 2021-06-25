package demo.design.level.low.parkinglot.services.parkinglot.core.services.implementations;

import demo.design.level.low.parkinglot.services.cache.ICacheService;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingSpotStatus;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingTicketStatus;
import demo.design.level.low.parkinglot.services.parkinglot.core.repositories.ParkingTicketStatusRepository;
import demo.design.level.low.parkinglot.services.parkinglot.core.services.IParkingTicketStatusService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ParkingTicketStatusService implements IParkingTicketStatusService {

    private final ParkingTicketStatusRepository parkingTicketStatusRepository;
    private final ICacheService cacheService;

    @Autowired
    public ParkingTicketStatusService(final ParkingTicketStatusRepository parkingTicketStatusRepository,
                                      final ICacheService cacheService) {
        this.parkingTicketStatusRepository = parkingTicketStatusRepository;
        this.cacheService = cacheService;
    }

    private final Collection<ParkingTicketStatus> findAll() {
        return this.parkingTicketStatusRepository.findAll();
    }

    private final ParkingTicketStatus findByName(final String name) {
        return this.parkingTicketStatusRepository.findByName(name);
    }


    private Map<String, ParkingTicketStatus> getAll() {
        Map<String, ParkingTicketStatus> parkingTicketStatuses = this.cacheService.get("parkingTicketStatuses");
        if (MapUtils.isEmpty(parkingTicketStatuses)) {
            synchronized(this) {
                parkingTicketStatuses = this.cacheService.get("parkingTicketStatuses");
                if (MapUtils.isEmpty(parkingTicketStatuses)) {
                    parkingTicketStatuses = new ConcurrentHashMap<>();
                    Collection<ParkingTicketStatus> parkingTicketStatusList = this.findAll();
                    if (CollectionUtils.isNotEmpty(parkingTicketStatusList)) {
                        for (ParkingTicketStatus parkingTicketStatus : parkingTicketStatusList) {
                            ParkingTicketStatus e = parkingTicketStatuses.get(parkingTicketStatus.getName().intern());
                            if (e == null) {
                                parkingTicketStatuses.put(parkingTicketStatus.getName(), parkingTicketStatus);
                            }
                        }
                    }
                }
                if (MapUtils.isNotEmpty(parkingTicketStatuses)) {
                    this.cacheService.put("parkingTicketStatuses", parkingTicketStatuses, 86400000L);
                }
            }
        }
        return parkingTicketStatuses;
    }
    

    @Override
    public ParkingTicketStatus getByName(final String name) {
        ParkingTicketStatus parkingTicketStatus = this.getAll().get(name);
        if (parkingTicketStatus == null) {
            synchronized (name) {
                parkingTicketStatus = this.getAll().get(name);
                if (parkingTicketStatus == null) {
                    parkingTicketStatus = this.findByName(name);
                    if (parkingTicketStatus != null) {
                        this.getAll().put(name, parkingTicketStatus);
                    }
                }
            }
        }
        return parkingTicketStatus;
    }
}
