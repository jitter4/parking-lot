package com.example.parkinglot.services;

import com.example.parkinglot.entities.Spot;
import com.example.parkinglot.entities.Vehicle;
import com.example.parkinglot.enums.VehicleType;
import com.example.parkinglot.repos.SpotRepo;
import com.example.parkinglot.repos.VehicleRepo;
import com.example.parkinglot.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

@Component
public class ParkingLot {

    @Autowired
    private SpotRepo spotRepo;

    @Autowired
    private VehicleRepo vehicleRepo;

    @Value("${parkinglot}")
    private String parkingLot;

    private Comparator<Spot> c = (a, b) -> {
        if (a.getLevel() > b.getLevel()) return 1;
        if (a.getLevel() == b.getLevel() && a.getPos() > b.getPos()) return 1;
        return -1;
    };

    private Queue<Spot> smallSpots  = new PriorityQueue<>(c);
    private Queue<Spot> mediumSpots = new PriorityQueue<>(c);
    private Queue<Spot> largeSpots  = new PriorityQueue<>(c);

    @PostConstruct
    public void init() {
        List<Map<String, Integer>> l = JsonUtils.serialize(parkingLot, List.class);
        for (int i = 0; i < l.size(); i++) {
            Map<String, Integer> m = l.get(i);
            int level  = m.get("level");
            int small  = m.get("small");
            int medium = m.get("medium");
            int large  = m.get("large");
            for (int k = 0; k < small;  k++) addToQueue (smallSpots,  level, k, VehicleType.small);
            for (int k = 0; k < medium; k++) addToQueue (mediumSpots, level, k, VehicleType.medium);
            for (int k = 0; k < large;  k++) addToQueue (largeSpots,  level, k, VehicleType.large);
        }
    }

    void addToQueue (Spot s) {
        switch (s.getType()) {
            case small:  smallSpots.add(s);  break;
            case medium: mediumSpots.add(s); break;
            case large:  largeSpots.add(s);  break;
        }
    }

    void addToQueue(Queue<Spot> q, int level, int pos, VehicleType type) {
        Spot s = spotRepo.findByLevelAndPos(level, pos);
        if (null != s && s.getVehicle() != null) {
            return;
        }
        q .add(new Spot(type, level, pos));
    }

    private Spot getSpot(VehicleType type) {
        switch (type) {
            case small: {
                synchronized (smallSpots) {
                    return smallSpots.poll();
                }
            }
            case medium: {
                synchronized (mediumSpots) {
                    return mediumSpots.poll();
                }
            }
            case large: {
                synchronized (largeSpots) {
                    return largeSpots.poll();
                }
            }
        }
        return null;
    }

    public void delay (long milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public Spot park (Vehicle vehicle) {
        Spot spot = null;
        int retryCount = 0;
        while (spot == null && retryCount < 3) {
            spot = getSpot(vehicle.getType());
            if (spot == null) {
                delay(200);
                retryCount++;
            }
        }
        if (spot == null) {
            throw new RuntimeException("No Empty Spot");
        }
        vehicle = vehicleRepo.save(vehicle);
        spot.setVehicle(vehicle);
        vehicle.setSpot(spot);
        return spotRepo.save(spot);
    }

    public Vehicle unPark (Vehicle vehicle) {
        Spot s = vehicle.getSpot();
        s.setVehicle(null);
        spotRepo.save(s);
        addToQueue(s);
        return vehicle;
    }

}
