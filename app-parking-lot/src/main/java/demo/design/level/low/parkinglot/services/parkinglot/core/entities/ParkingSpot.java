package demo.design.level.low.parkinglot.services.parkinglot.core.entities;

import demo.design.level.low.parkinglot.services.parkinglot.core.entities.base.BaseAbstractEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Collection;

@Data
@NoArgsConstructor
@Entity
@Table(name = "parking_spots", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "number",
                "parking_lot_id"
        })
})
public class ParkingSpot extends BaseAbstractEntity {
    private int number;

    @OneToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private Vehicle vehicle;

    private boolean isVehicleOnSpots;

    @OneToOne
    @JoinColumn(name = "parking_status_id", referencedColumnName = "id")
    private ParkingStatus parkingStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parking_spot_type", referencedColumnName = "id")
    private ParkingSpotType parkingSpotType;

    @ManyToOne
    @JoinColumn(name = "parking_floor_id", nullable = false)
    private ParkingFloor parkingFloor;

    @ManyToOne
    @JoinColumn(name = "parking_lot_id", nullable = false)
    private ParkingLot parkingLot;

    @OneToMany(mappedBy = "parkingSpot", fetch = FetchType.LAZY)
    private Collection<VehicleTypeParkingSpotCount> vehicleTypeParkingSpotCounts;
}
