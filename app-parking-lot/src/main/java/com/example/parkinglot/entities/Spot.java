package com.example.parkinglot.entities;

import com.example.parkinglot.enums.VehicleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "spots")
public class Spot extends BaseAbstractEntity {

    @Column(name="type")
    @Enumerated(value = EnumType.STRING)
    private VehicleType type;
    private int level;
    private int pos;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_id")
    @JsonIgnore
    private Vehicle vehicle;

    public Spot(VehicleType type, int level, int pos) {
        this.type = type;
        this.level = level;
        this.pos = pos;
    }

}
