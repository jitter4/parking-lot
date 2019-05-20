package com.example.parkinglot.repos;

import com.example.parkinglot.entities.Spot;
import com.example.parkinglot.entities.Vehicle;
import com.example.parkinglot.enums.VehicleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpotRepo extends JpaRepository<Spot, String> {
    Spot findByLevelAndPos(int level, int pos);
    Spot findByVehicle(Vehicle vehicle);
    Spot findByVehicleRegNo(String regNo);
    Page<Spot> findByVehicleColor(String color, Pageable pageable);
    Page<Spot> findByVehicleType(VehicleType type, Pageable pageable);
}
