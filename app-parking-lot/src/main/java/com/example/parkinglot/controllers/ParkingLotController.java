package com.example.parkinglot.controllers;

import com.example.parkinglot.entities.Vehicle;
import com.example.parkinglot.enums.VehicleType;
import com.example.parkinglot.services.ParkingLotService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/secure/parking")
public class ParkingLotController {

    @Autowired
    ParkingLotService parkingLotManager;

    @PutMapping(value = "/vehicle/park")
    public ResponseEntity<?> park (@RequestBody Vehicle vehicle) {
        try {
            return new ResponseEntity<>(parkingLotManager.park(vehicle), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping(value = "/vehicle/unpark/{regno:.+}")
    public ResponseEntity<?> unPark (@PathVariable("regno") String regNo) {
        try {
            return new ResponseEntity<>(parkingLotManager.unPark(regNo), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping(value = "/vehicle/get/{regno:.+}")
    public ResponseEntity<?> getByRegNo (@PathVariable("regno") String regNo) {
        try {
            return new ResponseEntity<>(parkingLotManager.getVehicle(regNo), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping(value = "/vehicles")
    public ResponseEntity<?> getVehicle (@RequestParam(value = "color",   required = false) String color,
                                         @RequestParam(value = "type",    required = false) VehicleType type,
                                         @RequestParam(value = "inTime",  required = false) @DateTimeFormat(pattern="dd-MM-yyyy") Date inTime,
                                         @RequestParam(value = "outTime", required = false) @DateTimeFormat(pattern="dd-MM-yyyy") Date outTime,
                                         @RequestParam("p")  Optional<Integer> page,
                                         @RequestParam("ps") Optional<Integer> pageSize) {
        PageRequest pr = PageRequest.of(page.orElse(0),    pageSize.orElse(10));
        try {
            if (StringUtils.isNotBlank(color)) {
                return new ResponseEntity<>(parkingLotManager.getVehiclesByColor(color, pr), HttpStatus.FOUND);
            } else if (null != type) {
                return new ResponseEntity<>(parkingLotManager.getVehiclesByType(type, pr), HttpStatus.FOUND);
            }
            return new ResponseEntity<>(parkingLotManager.getVehiclesByDuration(inTime, outTime, pr), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

}
//02222019230000
//MMddyyyyhhmmss