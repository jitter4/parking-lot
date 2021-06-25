package demo.design.level.low.parkinglot.services.parkinglot.core.services.implementations;

import demo.design.level.low.parkinglot.services.cache.ICacheService;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingLot;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.VehicleTypeParkingSpotCount;
import demo.design.level.low.parkinglot.services.parkinglot.core.repositories.VehicleTypeParkingSpotCountRepository;
import demo.design.level.low.parkinglot.services.parkinglot.core.services.IVehicleTypeParkingSpotCountService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class VehicleTypeParkingSpotCountService implements IVehicleTypeParkingSpotCountService {

    private final VehicleTypeParkingSpotCountRepository vehicleTypeParkingSpotCountRepository;
    private final ICacheService cacheService;

    @Autowired
    public VehicleTypeParkingSpotCountService(
            final VehicleTypeParkingSpotCountRepository vehicleTypeParkingSpotCountRepository,
            final ICacheService cacheService) {
        this.vehicleTypeParkingSpotCountRepository = vehicleTypeParkingSpotCountRepository;
        this.cacheService = cacheService;
    }

    private List<VehicleTypeParkingSpotCount> findAll() {
        return this.vehicleTypeParkingSpotCountRepository.findAll();
    }


    private List<VehicleTypeParkingSpotCount> findByParkingLotAndVehicleTypeName(ParkingLot parkingLot, final String vehicleTypeName) {
        return this.vehicleTypeParkingSpotCountRepository.findByParkingLotIdAndVehicleTypeName(parkingLot.getId(), vehicleTypeName);
    }

    private Map<String, List<VehicleTypeParkingSpotCount>> getAll() {
        Map<String, List<VehicleTypeParkingSpotCount>> vehicleTypeParkingSpotCounts = this.cacheService.get("vehicleTypeParkingSpotCounts");
        if (MapUtils.isEmpty(vehicleTypeParkingSpotCounts)) {
            synchronized(this) {
                vehicleTypeParkingSpotCounts = this.cacheService.get("vehicleTypeParkingSpotCounts");
                if (MapUtils.isEmpty(vehicleTypeParkingSpotCounts)) {
                    vehicleTypeParkingSpotCounts = new ConcurrentHashMap<>();
                    List<VehicleTypeParkingSpotCount> vehicleTypeParkingSpotCountList = this.findAll();
                    if (CollectionUtils.isNotEmpty(vehicleTypeParkingSpotCountList)) {
                        for (VehicleTypeParkingSpotCount vehicleTypeParkingSpotCount : vehicleTypeParkingSpotCountList) {
                            List<VehicleTypeParkingSpotCount> e = vehicleTypeParkingSpotCounts.get(vehicleTypeParkingSpotCount.getVehicleType().getName().intern());
                            if (e == null) {
                                vehicleTypeParkingSpotCounts.put(vehicleTypeParkingSpotCount.getVehicleType().getName(), e = new ArrayList<>());
                            }
                            e.add(vehicleTypeParkingSpotCount);
                        }
                    }
                }
                if (MapUtils.isNotEmpty(vehicleTypeParkingSpotCounts)) {
                    this.cacheService.put("vehicleTypeParkingSpotCounts", vehicleTypeParkingSpotCounts, 86400000L);
                }
            }
        }
        return vehicleTypeParkingSpotCounts;
    }

    @Override
    public List<VehicleTypeParkingSpotCount> getByParkingLotAndVehicleTypeName(final ParkingLot parkingLot, final String vehicleTypeName) {
        List<VehicleTypeParkingSpotCount> vehicleTypeParkingSpotCountsByVehicleTypeName = this.getAll().get(vehicleTypeName);
        if (CollectionUtils.isEmpty(vehicleTypeParkingSpotCountsByVehicleTypeName)) {
            synchronized (vehicleTypeName) {
                vehicleTypeParkingSpotCountsByVehicleTypeName = this.getAll().get(vehicleTypeName);
                if (CollectionUtils.isEmpty(vehicleTypeParkingSpotCountsByVehicleTypeName)) {
                    vehicleTypeParkingSpotCountsByVehicleTypeName
                            = this.findByParkingLotAndVehicleTypeName(parkingLot, vehicleTypeName);
                    if (CollectionUtils.isNotEmpty(vehicleTypeParkingSpotCountsByVehicleTypeName)) {
                        this.getAll().put(vehicleTypeName, vehicleTypeParkingSpotCountsByVehicleTypeName);
                    }
                }
            }
        }
        return vehicleTypeParkingSpotCountsByVehicleTypeName;
    }

}
