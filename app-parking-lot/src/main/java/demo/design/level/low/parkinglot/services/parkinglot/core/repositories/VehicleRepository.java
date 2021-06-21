package demo.design.level.low.parkinglot.services.parkinglot.core.repositories;

import demo.design.level.low.parkinglot.services.parkinglot.core.entities.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Page<Vehicle> findAllByColor(String color, Pageable pageable);

    Page<Vehicle> findAllByType(String type, Pageable pageable);

    Vehicle findByRegistrationNumber(String registrationNumber);

    @Query(value = "select  * from vehicles where in_Time >= :inTime  and (out_time <= :outTime || out_time is NULL)", nativeQuery = true)
    Page<Vehicle> getByDuration(@Param("inTime") Date inTme, @Param("outTime") Date outTime, Pageable pageable);

    boolean existsByRegistrationNumber(String registrationNumber);

    Vehicle findTop1ByRegistrationNumberOrderByVersionDesc(String registrationNumber);
    Vehicle findTop1ByRegistrationNumberOrderByInTimeDesc(String registrationNumber);
}
