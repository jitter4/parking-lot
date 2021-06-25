package demo.design.level.low.parkinglot.services.parkinglot.core.repositories;

import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingSpotStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSpotStatusRepository extends JpaRepository<ParkingSpotStatus, Long> {
    ParkingSpotStatus findByName(String name);
}
