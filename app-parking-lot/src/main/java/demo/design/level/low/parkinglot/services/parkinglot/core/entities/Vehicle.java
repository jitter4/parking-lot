package demo.design.level.low.parkinglot.services.parkinglot.core.entities;

import demo.design.level.low.parkinglot.services.parkinglot.core.entities.base.BaseAbstractEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "vehicles")
public class Vehicle extends BaseAbstractEntity {

    @Column(nullable = false)
    @NotNull
    private String registrationNumber;

    @NotNull
    @Column(nullable = false)
    private String color;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "vehicle_type_id", nullable = false)
    private VehicleType type;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name= "in_time", nullable = false)
    private Date inTime;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name= "out_time")
    private Date outTime;

    @OneToOne
    @JoinColumn(name = "parking_spot_id", referencedColumnName = "id")
    private ParkingSpot parkingSpot;

    @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY)
    private Collection<ParkingTicket> parkingTicket;

    @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY)
    private Collection<VehicleTypeParkingSpotCount> vehicleTypeParkingSpotCounts;

    public Vehicle (final String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Vehicle(final String registrationNumber, final String color,
                   final VehicleType type, final ParkingSpot parkingSpot) {
        this.registrationNumber = registrationNumber;
        this.color = color;
        this.type = type;
        this.parkingSpot = parkingSpot;
    }

}
