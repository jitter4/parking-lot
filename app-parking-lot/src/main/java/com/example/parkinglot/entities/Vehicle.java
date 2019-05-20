package com.example.parkinglot.entities;

import com.example.parkinglot.enums.VehicleType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "vehicles")
public class Vehicle extends BaseAbstractEntity {

    @Column(name="vehicle_number", nullable = false)
    @NotNull
    private String regNo;

    @NotNull
    @Column(nullable = false)
    private String color;

    @NotNull
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private VehicleType type;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name= "in_time", nullable = false)
    private Date inTime;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name= "out_time")
    private Date outTime;

    @OneToOne
    @JoinColumn(name = "spot_id", referencedColumnName = "id")
    private Spot spot;

    public Vehicle (String regNo) {
        this.regNo = regNo;
    }

    public Vehicle(String regNo, String color, VehicleType type, Spot spot) {
        this.regNo = regNo;
        this.color = color;
        this.type = type;
        this.spot = spot;
    }

}
