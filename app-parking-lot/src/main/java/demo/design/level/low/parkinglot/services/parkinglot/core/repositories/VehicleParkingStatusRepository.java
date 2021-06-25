package demo.design.level.low.parkinglot.services.parkinglot.core.repositories;

import demo.design.level.low.parkinglot.services.parkinglot.core.entities.VehicleParkingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleParkingStatusRepository extends JpaRepository<VehicleParkingStatus, Long> {

    VehicleParkingStatus findByName(String name);

}
