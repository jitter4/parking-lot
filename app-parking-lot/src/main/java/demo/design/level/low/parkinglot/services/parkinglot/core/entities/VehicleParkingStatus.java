package demo.design.level.low.parkinglot.services.parkinglot.core.entities;

import demo.design.level.low.parkinglot.services.parkinglot.core.entities.base.BaseAbstractEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;

@Data
@NoArgsConstructor
@Entity
@Table(name = "vehicle_parking_statuses")
public class VehicleParkingStatus extends BaseAbstractEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "parkingStatus", fetch = FetchType.LAZY)
    private Collection<Vehicle> vehicles;

}
