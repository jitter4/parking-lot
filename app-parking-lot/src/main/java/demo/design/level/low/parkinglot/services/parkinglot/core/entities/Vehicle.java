package demo.design.level.low.parkinglot.services.parkinglot.core.entities;

import demo.design.level.low.parkinglot.services.parkinglot.core.entities.base.BaseAbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@AllArgsConstructor
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

    @NotNull
    @ManyToOne
    @JoinColumn(name = "vehicle_parking_status_id", nullable = false)
    private VehicleParkingStatus parkingStatus;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<ParkingSpot> parkingSpots;

    @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY)
    private Collection<ParkingTicket> parkingTicket;

}
