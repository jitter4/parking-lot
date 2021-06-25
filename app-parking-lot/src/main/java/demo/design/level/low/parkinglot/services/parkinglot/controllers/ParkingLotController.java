package demo.design.level.low.parkinglot.services.parkinglot.controllers;

import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingLot;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.Vehicle;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.VehicleType;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.VehicleTypeParkingSpotCount;
import demo.design.level.low.parkinglot.services.parkinglot.core.services.IParkingLotService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/secure/parking")
public class ParkingLotController {

    private final IParkingLotService parkingLotService;

    @Autowired
    public ParkingLotController(final IParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @PutMapping(value = "/vehicle/park")
    public ResponseEntity<?> park (@RequestBody Vehicle vehicle) {
        try {
            return new ResponseEntity<>(this.parkingLotService.park(null, vehicle), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping(value = "/vehicle/unpark/{parkingTicketNumber:.+}")
    public ResponseEntity<?> unPark (@PathVariable("parkingTicketNumber") String parkingTicketNumber) {
        try {
//            return new ResponseEntity<>(this.parkingLotService.unPark(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        return null;
    }

    @GetMapping(value = "/vehicle/get/{registrationNumber:.+}")
    public ResponseEntity<?> getByRegNo (@PathVariable("registrationNumber") String registrationNumber) {
        try {
            return new ResponseEntity<>(this.parkingLotService.getVehicleByRegistrationNumber(registrationNumber), HttpStatus.FOUND);
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
                return new ResponseEntity<>(this.parkingLotService.getVehiclesByColor(color, pr), HttpStatus.FOUND);
            } else if (null != type) {
                return new ResponseEntity<>(this.parkingLotService.getVehiclesByType(type, pr), HttpStatus.FOUND);
            }
            return new ResponseEntity<>(this.parkingLotService.getVehiclesByDuration(inTime, outTime, pr), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("addParkingLot")
    public ResponseEntity<?> addParkingLot(@RequestBody final ParkingLot parkingLot) {
        try {
            return new ResponseEntity<>(this.parkingLotService.addParkingLot(parkingLot), HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

}