package demo.design.level.low.parkinglot.services.parkinglot.core.entities.actors;

import demo.design.level.low.parkinglot.services.authentication.entities.User;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.ParkingLot;
import demo.design.level.low.parkinglot.services.parkinglot.core.entities.base.BaseAbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "parking_lot_administrators")
public class ParkingLotAdministrator extends BaseAbstractEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "parking_lot_id", nullable = false)
    private ParkingLot parkingLot;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "parking_lot_admins",
            joinColumns = @JoinColumn(name = "administrator_id"),
            inverseJoinColumns = @JoinColumn(name = "parking_lot_id"))
    private Set<ParkingLot> roles = new HashSet<>();

}
