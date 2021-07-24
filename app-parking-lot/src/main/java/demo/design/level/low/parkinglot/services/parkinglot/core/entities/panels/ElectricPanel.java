package demo.design.level.low.parkinglot.services.parkinglot.core.entities.panels;

import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingSpot;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.base.BaseAbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "electric_panels")
public class ElectricPanel extends BaseAbstractEntity {

    @Column(unique = true)
    private int number;

    private int payedForMinutes;
    private Date chargingStartTime;

    @OneToOne
    private ParkingSpot parkingSpot;
}
