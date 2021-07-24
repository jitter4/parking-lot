package demo.design.level.low.parkinglot.services.parkinglot.core.entities;

import demo.design.level.low.parkinglot.services.parkinglot.core.entities.base.BaseAbstractEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "parking_rates")
public class ParkingRate extends BaseAbstractEntity {

    private int hourStart;
    private int hourEnd;

    @ManyToOne
    @JoinColumn(name = "rate_id", referencedColumnName = "id")
    private Rate rate;

    @OneToOne
    private ParkingSpotType parkingSpotType;

    @ManyToOne
    @JoinColumn(name = "parking_lot_id", nullable = false)
    private ParkingLot parkingLot;

}
