package demo.design.level.low.parkinglot.services.parkinglot.core.services.implementations;

import demo.design.level.low.parkinglot.services.cache.ICacheService;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.VehicleParkingStatus;
import demo.design.level.low.parkinglot.services.parkinglot.core.repositories.VehicleParkingStatusRepository;
import demo.design.level.low.parkinglot.services.parkinglot.core.services.IVehicleParkingStatusService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class VehicleParkingStatusService implements IVehicleParkingStatusService {

    private final VehicleParkingStatusRepository vehicleParkingStatusRepository;
    private final ICacheService cacheService;

    @Autowired
    public VehicleParkingStatusService(final VehicleParkingStatusRepository vehicleParkingStatusRepository,
                                      final ICacheService cacheService) {
        this.vehicleParkingStatusRepository = vehicleParkingStatusRepository;
        this.cacheService = cacheService;
    }

    private final Collection<VehicleParkingStatus> findAll() {
        return this.vehicleParkingStatusRepository.findAll();
    }

    private final VehicleParkingStatus findByName(final String name) {
        return this.vehicleParkingStatusRepository.findByName(name);
    }


    private Map<String, VehicleParkingStatus> getAll() {
        Map<String, VehicleParkingStatus> vehicleParkingStatuses = this.cacheService.get("vehicleParkingStatuses");
        if (MapUtils.isEmpty(vehicleParkingStatuses)) {
            synchronized(this) {
                vehicleParkingStatuses = this.cacheService.get("vehicleParkingStatuses");
                if (MapUtils.isEmpty(vehicleParkingStatuses)) {
                    vehicleParkingStatuses = new ConcurrentHashMap<>();
                    Collection<VehicleParkingStatus> vehicleParkingStatusList = this.findAll();
                    if (CollectionUtils.isNotEmpty(vehicleParkingStatusList)) {
                        for (VehicleParkingStatus vehicleParkingStatus : vehicleParkingStatusList) {
                            VehicleParkingStatus e = vehicleParkingStatuses.get(vehicleParkingStatus.getName().intern());
                            if (e == null) {
                                vehicleParkingStatuses.put(vehicleParkingStatus.getName(), vehicleParkingStatus);
                            }
                        }
                    }
                }
                if (MapUtils.isNotEmpty(vehicleParkingStatuses)) {
                    this.cacheService.put("vehicleParkingStatuses", vehicleParkingStatuses, 86400000L);
                }
            }
        }
        return vehicleParkingStatuses;
    }

    @Override
    public VehicleParkingStatus getByName(String name) {
        VehicleParkingStatus vehicleParkingStatus = this.getAll().get(name);
        if (vehicleParkingStatus == null) {
            synchronized (name) {
                vehicleParkingStatus = this.getAll().get(name);
                if (vehicleParkingStatus == null) {
                    vehicleParkingStatus = this.findByName(name);
                    if (vehicleParkingStatus != null) {
                        this.getAll().put(name, vehicleParkingStatus);
                    }
                }
            }
        }
        return vehicleParkingStatus;
    }
}
