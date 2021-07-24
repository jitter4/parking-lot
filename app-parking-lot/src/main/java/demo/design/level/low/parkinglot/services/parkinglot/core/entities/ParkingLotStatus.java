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
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "parking_lot_status")
public class ParkingLotStatus extends BaseAbstractEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "parkingLotStatus", fetch = FetchType.LAZY)
    private Collection<ParkingLot> parkingLot;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParkingLotStatus)) return false;
        if (!super.equals(o)) return false;
        ParkingLotStatus that = (ParkingLotStatus) o;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName());
    }
}
