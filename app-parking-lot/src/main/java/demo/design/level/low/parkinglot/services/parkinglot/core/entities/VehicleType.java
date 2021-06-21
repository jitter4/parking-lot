package demo.design.level.low.parkinglot.services.parkinglot.core.entities;

import demo.design.level.low.parkinglot.services.parkinglot.core.entities.base.BaseAbstractEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CollectionType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;

@Data
@NoArgsConstructor
@Entity
@Table(name = "vehicle_types")
public class VehicleType extends BaseAbstractEntity {

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
    private Collection<Vehicle> vehicles;

}
