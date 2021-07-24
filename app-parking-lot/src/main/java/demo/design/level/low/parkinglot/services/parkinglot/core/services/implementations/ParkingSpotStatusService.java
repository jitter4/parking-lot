package demo.design.level.low.parkinglot.services.parkinglot.core.services.implementations;

import demo.design.level.low.parkinglot.services.cache.ICacheService;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingSpotStatus;
import demo.design.level.low.parkinglot.services.parkinglot.core.repositories.ParkingSpotStatusRepository;
import demo.design.level.low.parkinglot.services.parkinglot.core.services.IParkingSpotStatusService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ParkingSpotStatusService implements IParkingSpotStatusService {

    private final ParkingSpotStatusRepository parkingSpotStatusRepository;
    private final ICacheService cacheService;

    @Autowired
    public ParkingSpotStatusService(final ParkingSpotStatusRepository parkingSpotStatusRepository,
                                    final ICacheService cacheService) {
        this.parkingSpotStatusRepository = parkingSpotStatusRepository;
        this.cacheService = cacheService;
    }

    private List<ParkingSpotStatus> getAll() {
        return this.parkingSpotStatusRepository.findAll();
    }

    private ParkingSpotStatus findByName(final String name) {
        return this.parkingSpotStatusRepository.findByName(name);
    }

    private Map<String, ParkingSpotStatus> getParkingSpotStatuses() {
        Map<String, ParkingSpotStatus> parkingSpotStatuses = this.cacheService.get("parkingSpotStatuses");
        if (MapUtils.isEmpty(parkingSpotStatuses)) {
            synchronized(this) {
                parkingSpotStatuses = this.cacheService.get("parkingSpotStatuses");
                if (MapUtils.isEmpty(parkingSpotStatuses)) {
                    parkingSpotStatuses = new ConcurrentHashMap<>();
                    List<ParkingSpotStatus> parkingSpotStatusList = this.getAll();
                    if (CollectionUtils.isNotEmpty(parkingSpotStatusList)) {
                        for (ParkingSpotStatus parkingSpotStatus : parkingSpotStatusList) {
                            ParkingSpotStatus e = parkingSpotStatuses.get(parkingSpotStatus.getName().intern());
                            if (e == null) {
                                parkingSpotStatuses.put(parkingSpotStatus.getName(), parkingSpotStatus);
                            }
                        }
                    }
                }
                if (MapUtils.isNotEmpty(parkingSpotStatuses)) {
                    this.cacheService.put("parkingSpotStatuses", parkingSpotStatuses, 86400000L);
                }
            }
        }
        return parkingSpotStatuses;
    }

    @Override
    public ParkingSpotStatus getByName(final String name) {
        ParkingSpotStatus parkingSpotStatus = this.getParkingSpotStatuses().get(name);
        if (parkingSpotStatus == null) {
            synchronized (name) {
                parkingSpotStatus = this.getParkingSpotStatuses().get(name);
                if (parkingSpotStatus == null) {
                    parkingSpotStatus = this.findByName(name);
                    if (parkingSpotStatus != null) {
                        this.getParkingSpotStatuses().put(name, parkingSpotStatus);
                    }
                }
            }
        }
        return parkingSpotStatus;
    }
}
