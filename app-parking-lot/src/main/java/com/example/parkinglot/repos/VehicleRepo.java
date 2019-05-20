package com.example.parkinglot.repos;

import com.example.parkinglot.entities.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle, Long> {

    Page<Vehicle> findAllByColor (String color, Pageable pageable);

    Page<Vehicle> findAllByType(String type, Pageable pageable);

    Vehicle findByRegNo(String regNo);

    @Query(value = "select  * from vehicles where in_Time >= :inTime  and (out_time <= :outTime || out_time is NULL)", nativeQuery = true)
    Page<Vehicle> getByDuration(@Param("inTime") Date inTme, @Param("outTime") Date outTime, Pageable pageable);

    boolean existsByRegNo(String regNo);

    Vehicle findTop1ByRegNoOrderByVersionDesc(String regNo);
    Vehicle findTop1ByRegNoOrderByInTimeDesc(String regNo);
}
