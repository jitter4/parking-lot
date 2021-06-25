package demo.design.level.low.parkinglot.services.parkinglot.core.repositories;

import demo.design.level.low.parkinglot.services.parkinglot.core.entities.VehicleTypeParkingSpotCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleTypeParkingSpotCountRepository extends JpaRepository<VehicleTypeParkingSpotCount, Long> {

    List<VehicleTypeParkingSpotCount> findByParkingLotIdAndVehicleTypeName(Long parkingLotId, String vehicleTypeName);

}
