package demo.design.level.low.parkinglot.services.parkinglot.core.repositories;

import demo.design.level.low.parkinglot.services.parkinglot.core.entities.VehicleType;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingSpot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, String> {
    ParkingSpot findByVehicleId(Long vehicleId);
    ParkingSpot findByVehicleRegistrationNumber(String registrationNumber);
    Page<ParkingSpot> findByVehicleColor(String color, Pageable pageable);
    Page<ParkingSpot> findByVehicleType(VehicleType type, Pageable pageable);
    List<ParkingSpot> findByParkingLotIdAndParkingSpotTypeNameAndAndParkingSpotStatusNameOrderByNumberAscParkingFloorNumberAsc(
            Long parkingLotId, String parkingSpotTypeName, String parkingStatusName, Pageable pageable);
}
