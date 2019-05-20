package com.example.parkinglot.services.impl;

import com.example.parkinglot.services.ParkingLot;
import com.example.parkinglot.entities.Spot;
import com.example.parkinglot.entities.Vehicle;
import com.example.parkinglot.enums.VehicleType;
import com.example.parkinglot.repos.SpotRepo;
import com.example.parkinglot.repos.VehicleRepo;
import com.example.parkinglot.services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {

    @Autowired
    private ParkingLot parkingLot;

    @Autowired
    private SpotRepo spotRepo;

    @Autowired
    private VehicleRepo vehicleRepo;

    @Override
    public Spot park(Vehicle vehicle) {
        if (StringUtils.isEmpty(vehicle.getRegNo())) {
            throw new RuntimeException("Empty Reg No");
        }
        if (StringUtils.isEmpty(vehicle.getColor())) {
            throw new RuntimeException("Empty color");
        }
        if (null == vehicle.getType()) {
            throw new RuntimeException("Invalid type");
        }
        Vehicle v = vehicleRepo.findTop1ByRegNoOrderByInTimeDesc(vehicle.getRegNo());
        if (null == v || v.getOutTime() != null) {
            vehicle.setInTime(new Date());
            return parkingLot.park(vehicle);
        }
        return spotRepo.findByVehicle(v);
    }

    @Override
    public Vehicle unPark (String regno) {
        if (StringUtils.isEmpty(regno)) {
            throw new RuntimeException("Empty Reg No");
        }
        Vehicle v = vehicleRepo.findTop1ByRegNoOrderByInTimeDesc(regno);
        if (null == v) {
            throw new RuntimeException("Vehicle Never Parked");
        }
        if (v.getSpot().getVehicle() == null || (!v.getSpot().getVehicle().getRegNo().equals(regno))) {
            throw new RuntimeException("Vehicle Not Parked");
        }
        v.setOutTime(new Date());
        parkingLot.unPark(v);
        return v;
    }

    @Override
    public Vehicle getVehicle(String regNo) {
        return vehicleRepo.findTop1ByRegNoOrderByInTimeDesc(regNo);
    }

    @Override
    public Page<Vehicle> getVehiclesByColor(String color, PageRequest pr) {
        return spotRepo.findByVehicleColor(color, pr).map(Spot::getVehicle);
    }

    @Override
    public Page<Vehicle> getVehiclesByType(VehicleType type, PageRequest pr) {
        return spotRepo.findByVehicleType(type, pr).map(Spot::getVehicle);
    }

    @Override
    public Page<Vehicle> getVehiclesByDuration(Date inTme, Date outTime, PageRequest pr) {
        return vehicleRepo.getByDuration(inTme, outTime, pr);
    }
}